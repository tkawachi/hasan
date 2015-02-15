package hasan

import scala.annotation.tailrec
import scala.util.Random

case class Simulator(
    config: SimulatorConfig,
    metrics: TradeMetrics,
    management: RiskManager) {

  def singleRun(): Double = {
    val accountStart = 1
    @tailrec
    def loop(history: AccountHistory): Double = {
      if (drawDown(history.equityCurve) >= config.ruinDrawDown) {
        ruinProb(history)
      } else if (history.tradeResults.size >= config.enoughTrades) {
        0
      } else {
        val newResult = trade(metrics, management.risked(accountStart, history.balance))
        loop(history :+ newResult)
      }
    }
    loop(AccountHistory(accountStart))
  }

  def run(n: Int): Double = {
    require(n > 0)
    val probs = (0 until n).map(_ => singleRun())
    probs.sum / probs.size
  }

  def drawDown(curve: IndexedSeq[Double]): Double = {
    val high = curve.max
    (high - curve.last) / high
  }

  def ruinProb(history: AccountHistory): Double = {
    val highIdx = history.equityCurve.zipWithIndex.maxBy(_._1)._2
    val nTradesFromHigh = history.equityCurve.size - highIdx - 1
    val tradesFromHigh = history.tradeResults.takeRight(nTradesFromHigh)
    val nLose = tradesFromHigh.count(_ < 0)
    nLose.toDouble / nTradesFromHigh
  }

  def trade(metrics: TradeMetrics, risked: Double): Double = {
    val rnd = Random.nextDouble()
    if (rnd >= 1 - metrics.accuracy) risked * metrics.payoffRatio else -risked
  }
}

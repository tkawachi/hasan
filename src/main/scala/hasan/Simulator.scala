package hasan

import scala.annotation.tailrec
import scala.util.Random

case class Simulator(
                      ruinPointDrawDown: Double,
                      accuracy: Double, payoffRatio: Double,
                      management: RiskManagement) {

  def run(): Double = {
    val accountStart = 1
    @tailrec
    def loop(history: AccountHistory): Double = {
      if (drawDown(history.equityCurve) >= ruinPointDrawDown) {
        ruinProb(history)
      } else if (history.equityCurve.last > 200000000 || history.tradeResults.size >= 10000) {
        0
      } else {
        loop(history :+ trade(management.risked(accountStart, history.balance)))
      }
    }
    loop(AccountHistory(accountStart))
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

  def trade(risked: Double): Double = {
    val rnd = Random.nextDouble()
    if (rnd >= 1 - accuracy) risked * payoffRatio else -risked
  }
}

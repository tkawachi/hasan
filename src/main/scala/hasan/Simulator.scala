package hasan

import java.util.concurrent.ThreadLocalRandom

import com.typesafe.scalalogging.StrictLogging

import scala.annotation.tailrec

case class Simulator(
    metrics: TradeMetrics,
    management: RiskManager,
    ruinPoint: Double = 0.1) extends StrictLogging {

  /**
   * @return true when ruined
   */
  def singleRun(): Boolean = {
    val balanceStart = 1.0
    val enoughBalance = 100.0
    @tailrec
    def loop(balance: Double): Boolean = {
      if (balance <= ruinPoint) true
      else if (balance >= enoughBalance) false
      else {
        val risked = management.risked(balanceStart, balance)
        loop(balance + trade(metrics, risked))
      }
    }
    loop(balanceStart)
  }

  def run(n: Int = 100000): Double = {
    require(n > 0)
    val probs = (0 until n).par.map(_ => singleRun())
    probs.count(identity).toDouble / probs.size
  }

  def trade(metrics: TradeMetrics, risked: Double): Double = {
    val rnd = ThreadLocalRandom.current().nextDouble()
    if (rnd < metrics.accuracy) risked * metrics.payoffRatio else -risked
  }
}

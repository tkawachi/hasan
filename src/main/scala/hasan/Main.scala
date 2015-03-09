package hasan

object Main {
  def main(args: Array[String]) = {
    val metrics = new TradeMetrics(0.3743, 13.13 / 6.61)
    println(metrics)
    (1 to 20).foreach { i =>
      val risk = 0.001 * i + 0.005
      val manager = FixedRatioRiskManager(risk)
      val sim = new Simulator(metrics, manager, 0.5)
      val prob = sim.run()
      println(f"risk $risk%.3f ruin prob $prob%.4f")
    }
  }
}

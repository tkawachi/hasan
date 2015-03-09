package hasan

object Main {
  def main(args: Array[String]) = {
    val metrics = new TradeMetrics(0.3743, 13.13 / 6.61)
    val config = new SimulatorConfig(0.5, 10000)
    val manager = FixedRatioRiskManager(0.009)
    val sim = new Simulator(config, metrics, manager)
    println(metrics)
    println(manager)
    println(sim.run(100))
  }
}

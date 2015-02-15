package hasan

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object Main extends JSApp {
  @JSExport
  override def main() = {
    val metrics = new TradeMetrics(0.5, 1.1)
    val config = new SimulatorConfig(0.5, 10000)
    val manager = FixedRatioRiskManager(0.05)
    val sim = new Simulator(config, metrics, manager)
    println(sim.run(1000))
  }
}

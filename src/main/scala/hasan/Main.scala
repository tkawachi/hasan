package hasan

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object Main extends JSApp {
  @JSExport
  override def main() = {
    val sim = new Simulator(0.5, 0.5, 1.1, FixedRatioRiskManagement(0.05))
    println(sim.run())
  }
}

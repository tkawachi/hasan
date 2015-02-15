package hasan

trait RiskManager {
  def risked(startBalance: Double, balance: Double): Double
}

case class FixedRatioRiskManager(ratio: Double) extends RiskManager {
  override def risked(startBalance: Double, balance: Double) = balance * ratio
}

case class FixedAmountRiskManager(ratio: Double) extends RiskManager {
  override def risked(startBalance: Double, balance: Double) = startBalance * ratio
}

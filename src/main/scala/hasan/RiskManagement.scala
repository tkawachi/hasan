package hasan

trait RiskManagement {
  def risked(startBalance: Double, balance: Double): Double
}

case class FixedRatioRiskManagement(ratio: Double) extends RiskManagement {
  override def risked(startBalance: Double, balance: Double) = balance * ratio
}

case class FixedAmountRiskManagement(ratio: Double) extends RiskManagement {
  override def risked(startBalance: Double, balance: Double) = startBalance * ratio
}

package hasan

case class AccountHistory private (equityCurve: IndexedSeq[Double], tradeResults: IndexedSeq[Double]) {
  def balance: Double = equityCurve.last

  def :+(result: Double): AccountHistory =
    new AccountHistory(equityCurve :+ (equityCurve.last + result), tradeResults :+ result)
}

object AccountHistory {
  def apply(balance: Double): AccountHistory = new AccountHistory(Vector(balance), Vector())
}

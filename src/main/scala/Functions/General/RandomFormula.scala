package Functions.General

import Core.SimState

import scala.collection.mutable

object RandomFormula {

  def getFormulas: mutable.HashMap[String, (SimState, Double) => (Double, Double)] = {
    new mutable.HashMap[String, (SimState, Double) => (Double, Double)]
  }
}

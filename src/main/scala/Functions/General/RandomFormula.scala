package Functions.General

import Core.SimState
import Interfaces.FormulaInterface

import scala.collection.mutable

class RandomFormula extends FormulaInterface{

  def getFormulas: mutable.HashMap[String, (SimState,Double) => (Double, Double)] = {
    new mutable.HashMap[String, (SimState,Double) => (Double, Double)]
  }
}

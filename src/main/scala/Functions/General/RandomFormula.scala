package Functions.General

import Interfaces.FormulaInterface
import models.SimModel

import scala.collection.mutable

class RandomFormula extends FormulaInterface{

  def getFormulas: mutable.HashMap[String, (SimModel,Double) => (Double, Double)] = {
    new mutable.HashMap[String, (SimModel,Double) => (Double, Double)]
  }
}

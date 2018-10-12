package Functions.General

import Interfaces.FormulaInterface
import models.SimModel

import scala.collection.mutable

class AverageFormula extends FormulaInterface{
  def critChance(simModel: SimModel, customMod: Double = 0): (Double,Double)={
    (simModel.critResult, 2)
  }

  def getFormulas: mutable.HashMap[String, (SimModel, Double) => (Double, Double)] ={
    val mutMap: mutable.HashMap[String, (SimModel, Double) => (Double, Double)] = new mutable.HashMap[String, (SimModel, Double) => (Double, Double)]()
    mutMap.put("CheckCrit", critChance)

    mutMap
  }
}

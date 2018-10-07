package Functions.General

import Interfaces.FormulaInterface
import models.SimModel

import scala.collection.mutable

class AverageFormula extends FormulaInterface{
  def critChance(simModel: SimModel): (Double,Double)={
    (((simModel.statModel.modifierModel.critMod * (simModel.statModel.secondaryAttributeModel.criticalHit - simModel.statModel.modifierModel.substatLevelMod))/simModel.statModel.modifierModel.divisorLevelMod)/10, 2)
  }

  def getFormulas: mutable.HashMap[String, SimModel => (Double, Double)] ={
    val mutMap: mutable.HashMap[String, SimModel => (Double, Double)] = new mutable.HashMap[String, SimModel => (Double, Double)]()
    mutMap.put("Crit", critChance)

    mutMap
  }
}

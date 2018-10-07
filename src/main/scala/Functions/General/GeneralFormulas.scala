package Functions.General

import Interfaces.FormulaInterface
import models.SimModel

import scala.collection.mutable

class GeneralFormulas extends FormulaInterface{
  def damageFormula(simModel: SimModel): (Double, Double) ={
    val statModel = simModel.statModel
    val detMulti = 1+(((statModel.modifierModel.detMod * (statModel.secondaryAttributeModel.determination - statModel.modifierModel.mainstatLevelMod))/statModel.modifierModel.divisorLevelMod)/1000)
    val mainMulti = ((125 * (statModel.attributes.mainStatValue - 292)/292) + 100)/100
    val weaponMulti = (statModel.modifierModel.mainstatLevelMod * statModel.modifierModel.jobMod/1000) + statModel.weaponDamage
    (weaponMulti*detMulti*mainMulti, 1)
  }

  def getFormulas: mutable.HashMap[String, SimModel => (Double, Double)] = {
    val mutMap: mutable.HashMap[String, SimModel => (Double, Double)] = new mutable.HashMap[String, SimModel => (Double, Double)]()
    mutMap.put("Damage", damageFormula)

    mutMap
  }
}

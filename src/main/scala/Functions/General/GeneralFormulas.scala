package Functions.General

import Interfaces.FormulaInterface
import models.SimModel

import scala.collection.mutable

class GeneralFormulas extends FormulaInterface{
  def damageFormula(simModel: SimModel, customMod: Double = 0): (Double, Double) ={
    val statModel = simModel.statModel
    val detMulti = 1+(((statModel.modifierModel.detMod * (statModel.secondaryAttributeModel.determination - statModel.modifierModel.mainstatLevelMod))/statModel.modifierModel.divisorLevelMod)/1000)
    val mainMulti = ((125 * (statModel.attributes.mainStatValue - 292)/292) + 100)/100
    val weaponMulti = (statModel.modifierModel.mainstatLevelMod * statModel.modifierModel.jobMod/1000) + statModel.weaponDamage
    (weaponMulti*detMulti*mainMulti, 1)
  }

  def skillSpeedFormula(simModel: SimModel, customMod: Double = 2.5): (Double, Double)= {
    (1000-(130*((simModel.statModel.secondaryAttributeModel.skillSpeed - simModel.statModel.modifierModel.substatLevelMod)/simModel.statModel.modifierModel.divisorLevelMod)),0)
  }

  def spellSpeedFormula(simModel: SimModel, customMod: Double = 2.5): (Double, Double)= {
    (1000-(130*((simModel.statModel.secondaryAttributeModel.spellSpeed - simModel.statModel.modifierModel.substatLevelMod)/simModel.statModel.modifierModel.divisorLevelMod)),0)
  }

  def critChanceFormula(simModel: SimModel, customMod: Double = 0): (Double,Double)={
    (((simModel.statModel.modifierModel.critMod * (simModel.statModel.secondaryAttributeModel.criticalHit - simModel.statModel.modifierModel.substatLevelMod))/simModel.statModel.modifierModel.divisorLevelMod)/10, 0)
  }

  def dhFormula(simModel: SimModel, customMod: Double = 0): (Double,Double)={
    (((simModel.statModel.modifierModel.dhMod * (simModel.statModel.secondaryAttributeModel.directHit - simModel.statModel.modifierModel.substatLevelMod))/simModel.statModel.modifierModel.divisorLevelMod)/10, 0)
  }


  def getFormulas: mutable.HashMap[String, (SimModel, Double) => (Double, Double)] = {
    val mutMap: mutable.HashMap[String, (SimModel, Double) => (Double, Double)] = new mutable.HashMap[String, (SimModel, Double) => (Double, Double)]()
    mutMap.put("Damage", damageFormula)
    mutMap.put("Skill Speed", skillSpeedFormula)
    mutMap.put("Spell Speed", spellSpeedFormula)
    mutMap.put("Crit", critChanceFormula)
    mutMap.put("Direct Hit", dhFormula)

    mutMap
  }
}

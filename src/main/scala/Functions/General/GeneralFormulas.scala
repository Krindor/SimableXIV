package Functions.General

import Core.SimState
import Enums.FormulaNames
import Enums.FormulaNames.FormulaNames

import scala.collection.mutable

object GeneralFormulas {

  val mutMap: mutable.HashMap[FormulaNames, (SimState, Double) => (Double, Double)] = new mutable.HashMap[FormulaNames, (SimState, Double) => (Double, Double)]()

  def damageFormula(simModel: SimState, customMod: Double = 0): (Double, Double) = {
    val statModel = simModel.statModel
    val detMulti = 1 + (((statModel.modifierModel.detMod * (statModel.secondaryAttributeModel.determination - statModel.modifierModel.mainstatLevelMod)) / statModel.modifierModel.divisorLevelMod) / 1000)
    val mainMulti = ((125 * (statModel.attributes.mainStatValue - 292) / 292) + 100) / 100
    val weaponMulti = (statModel.modifierModel.mainstatLevelMod * statModel.modifierModel.jobMod / 1000) + statModel.weaponDamage
    (weaponMulti * detMulti * mainMulti, 1)
  }

  def skillSpeedFormula(simState: SimState, customMod: Double = 2.5): (Double, Double) = {
    (1000 - (simState.statModel.modifierModel.ssMod * ((simState.statModel.secondaryAttributeModel.skillSpeed - simState.statModel.modifierModel.substatLevelMod) / simState.statModel.modifierModel.divisorLevelMod)), 0)
  }

  def spellSpeedFormula(simState: SimState, customMod: Double = 2.5): (Double, Double) = {
    (1000 - (simState.statModel.modifierModel.ssMod * ((simState.statModel.secondaryAttributeModel.spellSpeed - simState.statModel.modifierModel.substatLevelMod) / simState.statModel.modifierModel.divisorLevelMod)), 0)
  }

  def critChanceFormula(simState: SimState, customMod: Double = 0): (Double, Double) = {
    (((simState.statModel.modifierModel.critMod * (simState.statModel.secondaryAttributeModel.criticalHit - simState.statModel.modifierModel.substatLevelMod)) / simState.statModel.modifierModel.divisorLevelMod) / 10, 0)
  }

  def dhFormula(simState: SimState, customMod: Double = 0): (Double, Double) = {
    (((simState.statModel.modifierModel.dhMod * (simState.statModel.secondaryAttributeModel.directHit - simState.statModel.modifierModel.substatLevelMod)) / simState.statModel.modifierModel.divisorLevelMod) / 10, 0)
  }

  mutMap.put(FormulaNames.Damage, damageFormula)
  mutMap.put(FormulaNames.SkillSpeed, skillSpeedFormula)
  mutMap.put(FormulaNames.SpellSpeed, spellSpeedFormula)
  mutMap.put(FormulaNames.Crit, critChanceFormula)
  mutMap.put(FormulaNames.DirectHit, dhFormula)
}

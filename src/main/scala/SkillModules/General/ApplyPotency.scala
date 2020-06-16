package SkillModules.General

import Blueprints.FunctionBlueprint
import Core.SimState
import Interfaces.SkillInterface

class ApplyPotency extends SkillInterface {

  private var isItACheck: Boolean = false
  private var successOrNormalPot: Double = _
  private var failPot: Double = _


  def buildSkill(skillBlueprint: FunctionBlueprint): Unit = {

    isItACheck = skillBlueprint.valueArray(0).toBoolean
    successOrNormalPot = skillBlueprint.valueArray(1).toDouble
    failPot = skillBlueprint.valueArray(2).toDouble

  }

  def run(simModel: SimState): Unit = {
    if (isItACheck) {
      if (simModel.checkSuccess) {
        simModel.potencyResult = successOrNormalPot
      }
      else simModel.potencyResult = failPot
    }
    else simModel.potencyResult = successOrNormalPot
  }

}

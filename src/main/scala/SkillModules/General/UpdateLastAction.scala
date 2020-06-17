package SkillModules.General

import Blueprints.FunctionBlueprint
import Core.SimState
import Enums.BuffMapTypes
import Interfaces.SkillModuleInterface

class UpdateLastAction extends SkillModuleInterface {

  val lastAction: String = "Last Action"
  var skillName: String = _

  def buildSkill(skillBlueprint: FunctionBlueprint): Unit = {

    skillName = skillBlueprint.valueArray(0)

  }

  def run(simState: SimState): Unit = {

    val buffModel = simState.buffModelMap(lastAction)
    buffModel.stringValue = skillName
    simState.buffMap(BuffMapTypes.Solo).put(lastAction, buffModel)

  }

}

package SkillModules.General

import Blueprints.FunctionBlueprint
import Core.SimState
import Enums.BuffMapTypes
import Enums.BuffMapTypes.BuffMapTypes
import Interfaces.SkillModuleInterface

class RemoveBuff extends SkillModuleInterface {

  var buffMapType: BuffMapTypes = _
  var buffName: String = _

  def buildSkill(skillBlueprint: FunctionBlueprint): Unit = {

    buffMapType = BuffMapTypes.withName(skillBlueprint.valueArray(0))
    buffName = skillBlueprint.valueArray(1)

  }

  def run(simState: SimState): Unit = {

    simState.buffMap(buffMapType).remove(buffName)

  }

}

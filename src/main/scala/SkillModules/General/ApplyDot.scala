package SkillModules.General

import Blueprints.FunctionBlueprint
import Core.SimState
import Enums.BuffMapTypes
import Enums.BuffMapTypes.BuffMapTypes
import Interfaces.SkillInterface

class ApplyDot extends SkillInterface {

  var buffMapType: BuffMapTypes = _
  var buffName: String = _

  def buildSkill(skillBlueprint: FunctionBlueprint): Unit = {

    buffMapType = BuffMapTypes.withName(skillBlueprint.valueArray(0))
    buffName = skillBlueprint.valueArray(1)
  }

  def run(simModel: SimState): Unit = {

    simModel.buffMap(buffMapType).put(buffName, simModel.buffModelMap(buffName))

  }

}

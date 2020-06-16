package SkillModules.General

import Blueprints.FunctionBlueprint
import Core.SimState
import Enums.BuffMapTypes
import Interfaces.SkillInterface


class AddDot extends SkillInterface {


  var dotName: String = _

  def buildSkill(skillBlueprint: FunctionBlueprint): Unit = {

    dotName = skillBlueprint.valueArray(0)


  }

  def run(simState: SimState): Unit = {

    val buffModel = simState.buffModelMap(dotName)
    buffModel.buffMap = simState.buffMap
    simState.buffMap(BuffMapTypes.DamageOverTime).put(dotName, buffModel)

  }

}

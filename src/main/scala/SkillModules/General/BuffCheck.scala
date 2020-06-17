package SkillModules.General

import Blueprints.FunctionBlueprint
import Core.SimState
import Enums.BuffMapTypes
import Interfaces.SkillModuleInterface

class BuffCheck extends SkillModuleInterface {

  private var targetBuff: String = _
  private var targetValue: String = _
  private var trueArray: Array[SkillModuleInterface] = _
  private var falseArray: Array[SkillModuleInterface] = _

  def buildSkill(skillBlueprint: FunctionBlueprint): Unit = {
    targetBuff = skillBlueprint.valueArray(0)
    targetValue = skillBlueprint.valueArray(1)
    //createNextSkills is defined in the trait
    val arrays = createNextSkills(skillBlueprint)
    trueArray = arrays._1
    falseArray = arrays._2
  }

  def run(simState: SimState): Unit = {
    if (simState.buffMap(BuffMapTypes.Solo)(targetBuff).stringValue == targetValue) {
      simState.checkSuccess = true
      for (function <- trueArray)
        function.run(simState)
    }
    else {
      simState.checkSuccess = false
      for (function <- falseArray)
        function.run(simState)
    }
  }
}

package SkillModules.General

import Blueprints.FunctionBlueprint
import Core.SimState
import Enums.BuffMapTypes
import Interfaces.SkillInterface

class BuffCheck extends SkillInterface {

  private var targetBuff: String = _
  private var targetValue: String = _
  private var trueArray: Array[SkillInterface] = _
  private var falseArray: Array[SkillInterface] = _

  def buildSkill(skillBlueprint: FunctionBlueprint): Unit = {
    targetBuff = skillBlueprint.valueArray(0)
    targetValue = skillBlueprint.valueArray(1)
    //createNextSkills is defined in the trait
    val arrays = createNextSkills(skillBlueprint)
    trueArray = arrays._1
    falseArray = arrays._2
  }

  def run(simModel: SimState): Unit = {
    if (simModel.buffMap(BuffMapTypes.Solo)(targetBuff).stringValue == targetValue) {
      simModel.checkSuccess = true
      for (function <- trueArray)
        function.run(simModel)
    }
    else {
      simModel.checkSuccess = false
      for (function <- falseArray)
        function.run(simModel)
    }
  }
}

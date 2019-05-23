package SkillModules.General

import Blueprints.SkillBlueprint
import Core.SimState
import Enums.BuffMapTypes
import Interfaces.SkillInterface

import scala.collection.mutable.ArrayBuffer

class StateCheck extends SkillInterface{

  private var targetState: String = _
  private var targetValue: String = _
  private val trueArray: ArrayBuffer[SkillInterface] = _
  private val falseArray: ArrayBuffer[SkillInterface] = _

  def buildSkill(skillBlueprint: SkillBlueprint): Unit ={
    targetState = skillBlueprint.valueArray(0)
    targetValue = skillBlueprint.valueArray(1)
    //createNewSkills is defined in the trait
    (trueArray, falseArray) = createNextSkills(skillBlueprint)
  }

  def run(simModel: SimState): Unit ={
    if (simModel.buffMap(BuffMapTypes.Solo)(targetState).stringValue == targetValue) {
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

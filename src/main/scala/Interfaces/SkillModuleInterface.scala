package Interfaces

import Blueprints.FunctionBlueprint
import Core.SimState
import Factories.DuplicateSmacker

import scala.collection.mutable.ArrayBuffer


trait SkillModuleInterface {

  //Creates the next set of skills/functions that the skill uses
  def createNextSkills(skillBlueprint: FunctionBlueprint): (Array[SkillModuleInterface], Array[SkillModuleInterface]) = {
    //Create arrays for each result
    var trueArray: ArrayBuffer[SkillModuleInterface] = new ArrayBuffer[SkillModuleInterface]
    var falseArray: ArrayBuffer[SkillModuleInterface] = new ArrayBuffer[SkillModuleInterface]
    //iterates over all the blueprints in the current layer of the skillblueprint, instantiate them with reflection and then builds them
    for (skill <- skillBlueprint.trueArray) {
      val newSkill = DuplicateSmacker.getSkillClass(skill.functionName)
      newSkill.buildSkill(skill)
      trueArray += newSkill
    }

    for (skill <- skillBlueprint.falseArray) {
      val newSkill = DuplicateSmacker.getSkillClass(skill.functionName)
      newSkill.buildSkill(skill)
      falseArray += newSkill
    }

    (trueArray.toArray, falseArray.toArray)
  }

  //Finds the appropriate SkillModule through reflection


  //Builds the current skill using the blueprint
  def buildSkill(skillBlueprint: FunctionBlueprint): Unit

  //runs the function
  def run(simState: SimState): Unit


}

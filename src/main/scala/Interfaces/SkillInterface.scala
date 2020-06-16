package Interfaces


import Blueprints.FunctionBlueprint
import Core.SimState

import scala.collection.mutable.ArrayBuffer


trait SkillInterface {

  //Creates the next set of skills/functions that the skill uses
  def createNextSkills(skillBlueprint: FunctionBlueprint): (Array[SkillInterface], Array[SkillInterface]) = {
    //Create arrays for each result
    var trueArray: ArrayBuffer[SkillInterface] = new ArrayBuffer[SkillInterface]
    var falseArray: ArrayBuffer[SkillInterface] = new ArrayBuffer[SkillInterface]
    //iterates over all the blueprints in the current layer of the skillblueprint, instantiate them with reflection and then builds them
    for (skill <- skillBlueprint.trueArray) {
      val newSkill = Class.forName(skill.functionName).newInstance().asInstanceOf[SkillInterface]
      newSkill.buildSkill(skill)
      trueArray += newSkill
    }

    for (skill <- skillBlueprint.falseArray) {
      val newSkill = Class.forName(skill.functionName).newInstance().asInstanceOf[SkillInterface]
      newSkill.buildSkill(skill)
      falseArray += newSkill
    }

    (trueArray.toArray, falseArray.toArray)
  }

  //Builds the current skill using the blueprint
  def buildSkill(skillBlueprint: FunctionBlueprint): Unit

  //runs the function
  def run(simState: SimState): Unit


}

package tests

import Blueprints.FunctionBlueprint


object JSONTest {
  def main(args: Array[String]): Unit = {

    val function = FunctionBlueprint("Apply Potency", Array("false", "150", "0"), Array.empty[FunctionBlueprint], Array.empty[FunctionBlueprint])
    val skill = SkillModelGenerator("Spinning Edge", "Weapon Skill", Array(function))

    val skillJSONSerializer = new SkillJSONSerializer
    skillJSONSerializer.createJSON(skill)

  }
}

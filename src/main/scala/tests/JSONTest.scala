package tests

import Blueprints.FunctionBlueprint
import models.SkillModel


object JSONTest {
  def main(args: Array[String]): Unit = {

    val function1 = FunctionBlueprint("ApplyPotency", Array("false", "150", "0"), Array.empty[FunctionBlueprint], Array.empty[FunctionBlueprint])
    val function2 = FunctionBlueprint("AddDot", Array("dummy"), Array.empty[FunctionBlueprint], Array.empty[FunctionBlueprint])
    val skill = SkillModel("Spinning Edge", "Weapon Skill", Array(function1, function2))

    val skillJSONSerializer = new SkillJSONSerializer
    val jsonString = skillJSONSerializer.createJSON(skill)
    val skillJSONDeserializer = new SkillJSONDeserializer
    val testSkill = skillJSONDeserializer.deserializeJSON(jsonString)
    print(testSkill.skillModuleIArray(1))

  }
}

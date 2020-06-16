package tests

import Blueprints.FunctionBlueprint



case class SkillModelGenerator(skillName: String, skillType: String, skillBlueprints: Array[FunctionBlueprint], baseRecast: Double = 2.5,
  castTime: Double = 0, applicationOffset: Double = 0)

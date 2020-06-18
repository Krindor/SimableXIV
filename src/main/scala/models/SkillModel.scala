package models


import Blueprints.FunctionBlueprint
import Core.SimState
import Interfaces.{RotationLogicInterface, SkillModelInterface, SkillModuleInterface}
import models.RotationLogic.ConditionLogic


case class SkillModel(
                       skillName: String,
                       skillType: String,
                       skillBlueprints: Array[FunctionBlueprint],
                       skillConditions: Array[ConditionLogic],
                       baseRecast: Double = 2.5,
                       castTime: Double = 0,
                       applicationOffset: Double = 0
                     ) extends SkillModelInterface{

  val skillModuleIArray: Array[SkillModuleInterface] = createSkillI(skillBlueprints)


  def run(simState: SimState): Unit = {

    runAttack(simState, skillModuleIArray)

  }

  def check(simState: SimState): (Boolean)={
    for (conditions <- skillConditions){
      if (!conditions.check(simState)) return (false)
    }
    (true)
  }

}

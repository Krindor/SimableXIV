package models


import Core.SimState
import Interfaces.{SkillModuleInterface, SkillModelInterface}


case class SkillModel(
                       skillName: String,
                       skillType: String,
                       baseRecast: Double = 2.5,
                       castTime: Double = 0,
                       applicationOffset: Double = 0
                     ) extends SkillModelInterface {

  val skillModuleIArray: Array[SkillModuleInterface] = createSkillI(skillName)


  def run(simState: SimState): Unit = {

    runAttack(simState, skillModuleIArray)

  }

}

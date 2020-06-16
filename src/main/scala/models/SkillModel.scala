package models


import Core.SimState
import Interfaces.{SkillInterface, SkillModelInterface}


case class SkillModel(skillName: String, skillType: String, baseRecast: Double = 2.5,
  castTime: Double = 0, applicationOffset: Double = 0) extends SkillModelInterface {
  //createSkillIArray is going to rewritten
  val skillI = createSkillI(skillName)
  val skillIArray: Array[SkillInterface] = Array(skillI)


  def run(simState: SimState): Unit = {

    runAttack(simState, skillIArray)

  }

}

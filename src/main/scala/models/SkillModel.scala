package models


import Interfaces.{SkillInterface, SkillModelInterface}



class SkillModel (val skillName: String, val skillType: String, val baseRecast: Double = 2.5,
                 val castTime: Double = 0, val applicationOffset: Double = 0) extends SkillModelInterface  {

  val skillIArray: Array[SkillInterface] = createSkillIArray(skillName)




}

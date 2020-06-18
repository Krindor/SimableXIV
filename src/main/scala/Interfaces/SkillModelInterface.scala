package Interfaces



import Blueprints.FunctionBlueprint
import Core.SimState
import Factories.DuplicateSmacker


import scala.collection.mutable.ArrayBuffer


trait SkillModelInterface {

  def runAttack(simState: SimState, skillIArray: Array[SkillModuleInterface]): Unit = {

    for (skill <- skillIArray) {

      skill.run(simState)
      print("ran check")
    }


  }

  //This method will be rewritten
  def createSkillI(funcArray: Array[FunctionBlueprint]): Array[SkillModuleInterface] = {



    val skillModuleInterfaceArray = new ArrayBuffer[SkillModuleInterface](funcArray.length)
    //Reflection to instantiate the first SkillModule of the SkillInterface
    for (functionBlueprint <- funcArray) {
      val skillInterface = DuplicateSmacker.getSkillClass(functionBlueprint.functionName)
      //Build the skill
      skillInterface.buildSkill(functionBlueprint)
      skillModuleInterfaceArray += skillInterface
    }
    skillModuleInterfaceArray.toArray
  }




}

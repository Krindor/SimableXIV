package Interfaces


import io.circe.parser._
import Blueprints.FunctionBlueprint
import Core.SimState
import Factories.DuplicateSmacker
import io.circe.generic.semiauto._
import io.circe._

import scala.collection.mutable.ArrayBuffer


trait SkillModelInterface {

  def runAttack(simState: SimState, skillIArray: Array[SkillModuleInterface]): Unit = {

    for (skill <- skillIArray) {

      skill.run(simState)
      print("ran check")
    }


  }

  //This method will be rewritten
  def createSkillI(jsonString: String): Array[SkillModuleInterface] = {
    //Get the file directory which contains the JSON files wanted


    //Create a list with all the files in the directory


    val decodeResult = decode[Array[FunctionBlueprint]](jsonString).toOption.get


    //Create the SkillInterface from the JSON files
    val skillModuleInterfaceArray = new ArrayBuffer[SkillModuleInterface](decodeResult.length)
    //Reflection to instantiate the first SkillModule of the SkillInterface
    for (functionBlueprint <- decodeResult) {
      val skillInterface = DuplicateSmacker.getSkillClass(functionBlueprint.functionName)
      //Build the skill
      skillInterface.buildSkill(functionBlueprint)
      skillModuleInterfaceArray += skillInterface
    }
    skillModuleInterfaceArray.toArray
  }

  implicit val fooDecoder: Decoder[Array[FunctionBlueprint]] = deriveDecoder[Array[FunctionBlueprint]]


}

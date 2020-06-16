package Interfaces





import io.circe.parser._
import Blueprints.FunctionBlueprint
import Core.SimState
import io.circe.generic.semiauto._
import io.circe._




trait SkillModelInterface {

  def runAttack(simModel: SimState, skillIArray: Array[SkillInterface]): Unit = {

    for (skill <- skillIArray) {

      skill.run(simModel)
      print(" ran check")
    }


  }

  //This method will be rewritten
  def createSkillI(jsonString: String): SkillInterface = {
    //Get the file directory which contains the JSON files wanted



    //Create a list with all the files in the directory



    val decodeResult = decode[FunctionBlueprint](jsonString).toOption.get


    //Create the SkillInterface from the JSON files

      //Reflection to instantiate the first part of the SkillInterface
      val skillInterface = Class.forName(decodeResult.functionName).newInstance().asInstanceOf[SkillInterface]
      //Build the skill
      skillInterface.buildSkill(decodeResult)
    skillInterface
    }

  implicit val fooDecoder: Decoder[FunctionBlueprint] = deriveDecoder[FunctionBlueprint]






}

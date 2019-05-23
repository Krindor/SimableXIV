package Interfaces

import java.io.File
import java.nio.file.Files

import Blueprints.SkillBlueprint
import Core.SimState
import com.github.plokhotnyuk.jsoniter_scala.core.{JsonValueCodec, readFromArray}
import com.github.plokhotnyuk.jsoniter_scala.macros.{CodecMakerConfig, JsonCodecMaker}

import scala.collection.mutable.ArrayBuffer

trait SkillModelInterface {

  def runAttack(simModel: SimState, skillIArray: Array[SkillInterface]): Unit ={

    for (skill <- skillIArray){

      skill.run(simModel)
      print(" ran check")
    }


  }

  //This method will probably be updated in the future
  def createSkillIArray(skillName: String): Array[SkillInterface] = {
    //Get the file directory which contains the JSON files wanted
    val directory = new File(skillName)
    //Create a list with all the files in the directory
    val fileList = directory.listFiles()

    val skillIArrayBuf = new ArrayBuffer[SkillInterface]()
    //Create the SkillInterface from the JSON files
    for (file <- fileList) {

      val byteArray = Files.readAllBytes(file.toPath)
      //Parse the JSON
      val skillBlueprint = readFromArray(byteArray)
      //Reflection to instantiate the first part of the SkillInterface
      val skillInterface = Class.forName(skillBlueprint.functionName).newInstance().asInstanceOf[SkillInterface]
      //Build the skill
      skillInterface.buildSkill(skillBlueprint)
      skillIArrayBuf += skillInterface
    }
    skillIArrayBuf.toArray


    implicit val codec: JsonValueCodec[SkillBlueprint] = JsonCodecMaker.make[SkillBlueprint](CodecMakerConfig())
  }

}

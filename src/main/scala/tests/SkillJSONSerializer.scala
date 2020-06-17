package tests


import io.circe.Printer
import io.circe.syntax._
import Blueprints.FunctionBlueprint
import io.circe.Encoder
import io.circe.generic.semiauto._


class SkillJSONSerializer {


  def createJSON(skillModelGenerator: SkillModelGenerator): String = {

    val json = skillModelGenerator.asJson
    val printed = Printer.spaces2.print(json)
    printed


  }

  implicit val modelEncoder: Encoder[SkillModelGenerator] = deriveEncoder[SkillModelGenerator]
  implicit val functionEncoder: Encoder[FunctionBlueprint] = deriveEncoder[FunctionBlueprint]


}

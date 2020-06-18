package tests


import io.circe.Printer
import io.circe.syntax._
import Blueprints.FunctionBlueprint
import io.circe.Encoder
import io.circe.generic.semiauto._
import models.SkillModel


class SkillJSONSerializer {


  def createJSON(SkillModel: SkillModel): String = {

    val json = SkillModel.asJson
    val printed = Printer.spaces2.print(json)
    printed


  }

  implicit val modelEncoder: Encoder[SkillModel] = deriveEncoder[SkillModel]
  implicit val functionEncoder: Encoder[FunctionBlueprint] = deriveEncoder[FunctionBlueprint]


}

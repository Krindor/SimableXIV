package tests

import Blueprints.FunctionBlueprint
import io.circe.generic.semiauto._
import io.circe.Decoder
import io.circe.parser._
import models.SkillModel

class SkillJSONDeserializer {

  def deserializeJSON(jsonString: String): SkillModel ={
    decode[SkillModel](jsonString).toOption.get
  }

  implicit val fooDecoder: Decoder[SkillModel] = deriveDecoder[SkillModel]
  implicit val functionEncoder: Decoder[FunctionBlueprint] = deriveDecoder[FunctionBlueprint]
}

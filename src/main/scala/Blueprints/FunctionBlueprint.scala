package Blueprints

import scala.scalajs.js.annotation.JSExportTopLevel


@JSExportTopLevel("FunctionBlueprint")
case class FunctionBlueprint(
  functionName: String,
  valueArray: Array[String],
  trueArray: Array[FunctionBlueprint],
  falseArray: Array[FunctionBlueprint]

)





package BaseClasses


import Core.SimState
import models._

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
@JSExportTopLevel("JobInfo")
class JobInfo(val statModel: StatModel, val jobName: String, val time: Int) {
@JSExport
  var simModel: SimState = _
  //def createSimModel(openerQueue: MutQueue[OpenerModel], nextAttack: NextAttack, generalFunctionMap: Map[GeneralFunctionNames, SimState => Unit], formulaMap: Map[String, (SimState, Double )=> (Double, Double)], statModel: StatModel,
  //attackMap: Map[String, SkillModel], attackFunctionMap: Map[String, (SimState, MutQueue[String]) => Unit], buffModelMap: Map[String, BuffModel], rotationLogic: StateCheck): Unit ={
  //simModel = new SimState(openerQueue, nextAttack, formulaMap, statModel, attackMap, attackFunctionMap, new StateCheck(new Array[(ConditionLogic, RotationLogicInterface)](1)))
  //}


}

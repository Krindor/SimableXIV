package BaseClasses


import Core.SimState
import Enums.GeneralFunctionNames.GeneralFunctionNames
import models.RotationLogic.{ConditionLogic, StateCheck}
import models._
import timers.NextAttack

import scala.collection.mutable.{Queue => MutQueue}
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("JobInfo")
class JobInfo(
               val statModel: StatModel,
               val jobName: String,
               val time: Int,
               val openerQueue: MutQueue[OpenerModel],
               val attackMap: Map[String, SkillModel],
               val buffModelMap: Map[String, BuffModel],
               val rotationLogic: StateCheck) {
  @JSExport
  var simState: SimState = _

  def createSimState(nextAttack: NextAttack): Unit = {
    simState = new SimState(
      openerQueue,
      nextAttack,
      statModel,
      attackMap,
      buffModelMap,
      rotationLogic
    )
  }


}

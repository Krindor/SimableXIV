package BaseClasses


import Core.SimState
import Enums.GeneralFunctionNames.GeneralFunctionNames
import models.RotationLogic.{ConditionLogic, StateCheck}
import models._
import timers.NextAttack

import scala.collection.mutable.{Queue => MutQueue}
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("JobInfo")
class JobInfo(val statModel: StatModel, val jobName: String, val time: Int) {
  @JSExport
  var simState: SimState = _

  def createSimState(
                      openerQueue: MutQueue[OpenerModel],
                      nextAttack: NextAttack,
                      generalFunctionMap: Map[GeneralFunctionNames, SimState => Unit],
                      formulaMap: Map[String, (SimState, Double) => (Double, Double)],
                      statModel: StatModel,
                      attackMap: Map[String, SkillModel],
                      attackFunctionMap: Map[String, (SimState, MutQueue[String]) => Unit],
                      buffModelMap: Map[String, BuffModel],
                      rotationLogic: StateCheck
                    ): Unit = {
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

package BaseClasses


import Core.SimState
import models.RotationLogic.{ConditionLogic, StateCheck}
import models._
import timers.NextAttack

import scala.collection.mutable.{Queue => MutQueue}
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

/**
 * Class containing the information for the specific character
 * @param statModel
 * @param jobName
 * @param openerQueue
 * @param attackMap
 * @param buffModelMap
 * @param rotationLogic
 * @param characterKey a unique key related to the character of this job
 */
@JSExportTopLevel("JobInfo")
class JobInfo(
               val statModel: StatModel,
               val jobName: String,
               val openerQueue: MutQueue[OpenerModel],
               val attackMap: Map[String, SkillModel],
               val buffModelMap: Map[String, BuffModel],
               val rotationLogic: StateCheck,
               val characterKey: String
             ) {
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

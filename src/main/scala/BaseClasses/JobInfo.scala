package BaseClasses


import Core.SimState
import Interfaces.RotationLogicInterface
import models.RotationLogic.{ConditionLogic, StateCheck}
import models._
import timers.NextAttack

import scala.collection.mutable
import scala.collection.mutable.{HashMap => MutMap, Queue => MutQueue}

class JobInfo(val statModel: StatModel, val jobName: String, val time:Int) {

  var simModel: SimState = _
  def createSimModel(openerQueue: MutQueue[OpenerModel], nextAttack: NextAttack, attackTypeMap: MutMap[String, SimState => Unit], formulaMap: MutMap[String, (SimState,Double)=> (Double, Double)], statModel: StatModel,
                     attackMap: MutMap[String, SkillModel], attackFunctionMap: MutMap[String, (SimState, mutable.Queue[String]) => Unit], buffMap: MutMap[String, BuffModel]): Unit ={
    simModel = new SimState(openerQueue, nextAttack, attackTypeMap, formulaMap, statModel, attackMap, attackFunctionMap, buffMap, new StateCheck(new Array[(ConditionLogic, RotationLogicInterface)](1)))
  }





}

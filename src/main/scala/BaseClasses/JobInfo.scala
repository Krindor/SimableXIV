package BaseClasses


import Interfaces.RotationLogicInterface
import models.RotationLogic.{ConditionLogic, StateCheck}
import models._
import timers.NextAttack

import scala.collection.mutable
import scala.collection.mutable.{HashMap => MutMap, Queue => MutQueue}

class JobInfo(val statModel: StatModel, val jobName: String, val time:Int) {
  var simModel: SimModel = _
  def createSimModel(openerQueue: MutQueue[OpenerModel], nextAttack: NextAttack, attackTypeMap: MutMap[String, SimModel => Unit], formulaMap: MutMap[String, (SimModel,Double)=> (Double, Double)], statModel: StatModel,
  attackMap: MutMap[String, SkillModel], attackFunctionMap: MutMap[String, (SimModel, mutable.Queue[String]) => Unit], buffMap: MutMap[String, BuffModel]): Unit ={
    simModel = new SimModel(openerQueue, nextAttack, attackTypeMap, formulaMap, statModel, attackMap, attackFunctionMap, buffMap, new StateCheck(new Array[(ConditionLogic, RotationLogicInterface)](1)))
  }





}

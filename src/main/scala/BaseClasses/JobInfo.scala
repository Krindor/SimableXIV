package BaseClasses


import models._
import timers.NextAttack

import scala.collection.mutable
import scala.collection.mutable.{HashMap => MutMap, Queue => MutQueue}

class JobInfo(val statModel: StatModel, val jobName: String, val time:Int) {
  var simModel: SimModel = _
  def createSimModel(openerQueue: MutQueue[OpenerModel], nextAttack: NextAttack, attackTypeMap: MutMap[String, SimModel => SimModel], formulaMap: MutMap[String, SimModel => (Double, Double)], statModel: StatModel,
  attackMap: MutMap[String, SkillModel], attackFunctionMap: MutMap[String, (SimModel, mutable.Queue[String]) => SimModel], buffMap: MutMap[String, BuffModel]): Unit ={
    simModel = new SimModel(openerQueue, nextAttack, attackTypeMap, formulaMap, statModel, attackMap, attackFunctionMap, buffMap)
  }





}

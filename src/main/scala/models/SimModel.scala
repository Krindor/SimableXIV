package models
import models.RotationLogic.StateCheck
import timers.NextAttack

import scala.collection.mutable
import scala.collection.mutable.{HashMap => MutMap, Queue => MutQueue}
//Contains variables handled in the core to ease passing them
class SimModel(var openerQueue: MutQueue[OpenerModel], var nextAttack: NextAttack, val generalFunctionMap: MutMap[String, SimModel => Unit], val formulaMap: MutMap[String, (SimModel, Double )=> (Double, Double)], var statModel: StatModel,
               val attackMap: MutMap[String, SkillModel], val attackFunctionMap: MutMap[String, (SimModel, mutable.Queue[String]) => Unit], val buffModelMap: MutMap[String, BuffModel], val rotationLogic: StateCheck) {
  var potencyResult: Double = 0
  var critResult: Double = 0
  var checkSuccess: Boolean = false
  val FormulaResult: MutMap[String, Double] = new MutMap[String, Double]()
  var timeChange: Double = 0
  var actionName: String = ""
  var snapShotBuffMap: MutMap[String, MutMap[String, BuffModel]] = new MutMap[String, MutMap[String,BuffModel]]()
  /*First Key is general type, like affects all, affects physical, affects a specific skill etc,
    Second Key is more specific types like resistance
    The last performed skill will also count as a buff and will be first key "State", second key "Last Action"
   */
  val buffMap: MutMap[String, MutMap[String, BuffModel]] = new MutMap[String, MutMap[String,BuffModel]]()
  buffMap.put("Solo", new MutMap[String, BuffModel])
  buffMap.put("Party", new MutMap[String, BuffModel])
  buffMap.put("Target", new MutMap[String, BuffModel])
  buffMap.put("Cooldowns", new MutMap[String, BuffModel])
  buffMap.put("DamageOverTime", new MutMap[String, BuffModel])

  def cleanResults(): Unit = {
    potencyResult = 0
    critResult = 0
    checkSuccess = false
    timeChange = 0
  }

  def updateTime(buffModel: BuffModel, double: Double): BuffModel ={
    buffModel.timeChange(double)
    buffModel
  }

  def updateTime(time: Double): Unit ={
    buffMap.transform((_, v) => v.transform((_,v)  =>  updateTime(v, time)))
  }
}

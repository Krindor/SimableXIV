package models
import models.RotationLogic.StateCheck
import timers.NextAttack


import scala.collection.mutable.{HashMap => MutMap, Queue => MutQueue, ArrayBuffer => MutArray}
/*
   Contains variables handled in the core to ease passing them
   Be careful when changing anything in this class as it could easily break other things
 */
class SimModel(val openerQueue: MutQueue[OpenerModel], val nextAttack: NextAttack, val generalFunctionMap: MutMap[String, SimModel => Unit], val formulaMap: MutMap[String, (SimModel, Double )=> (Double, Double)], val statModel: StatModel,
               val attackMap: MutMap[String, SkillModel], val attackFunctionMap: MutMap[String, (SimModel, MutQueue[String]) => Unit], val buffModelMap: MutMap[String, BuffModel], val rotationLogic: StateCheck) {
  var potencyResult: Double = 0
  var critResult: Double = 0
  var checkSuccess: Boolean = false
  val FormulaResult: MutMap[String, Double] = new MutMap[String, Double]()
  var timeChange: Double = 0
  var actionName: String = ""
  var snapShotBuffMap: MutMap[String, MutMap[String, BuffModel]] = new MutMap[String, MutMap[String,BuffModel]]()
  val eventLog: MutArray[String] = new MutArray[String]()
  var time: Double = 0
  /*First Key is general type, like affects all, affects physical, affects a specific skill etc,
    Second Key is more specific types like resistance
    The last performed combo skill will also count as a buff and will be first key "State", second key "Last Combo Skill"
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

  private def removeBuff(mapForRemoval: MutMap[String, BuffModel], name: String): Unit ={
    mapForRemoval.remove(name)
    eventLog += (time + ":" + name + " falls off")
  }

  def updateTime(time: Double): Unit ={
    //buffMap.transform((_, v) => v.transform((_,v)  =>  updateTime(v, time)))
    buffMap.values.foreach(firstMapValue =>
    {
      for (buffs <- firstMapValue){
        buffs._2.timeChange(time)
        if (buffs._2.valueMap("Time") <= 0){
          removeBuff(firstMapValue, buffs._1)
        }
      }

    }
    )
    timeChange += time

  }
}

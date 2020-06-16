package Core

import Enums.BuffMapTypes.BuffMapTypes
import Enums.FormulaNames.FormulaNames
import Enums.{BuffMapTypes, BuffValueNames}
import models.RotationLogic.StateCheck
import models.{BuffModel, OpenerModel, SkillModel, StatModel}
import timers.NextAttack
import Functions.General.GeneralFunctions

import scala.collection.mutable.{ArrayBuffer => MutArray, HashMap => MutMap, Queue => MutQueue}

/*
   Contains variables handled in the core to ease passing them
   Be careful when changing anything in this class as it could easily break other things
 */
class SimState(val openerQueue: MutQueue[OpenerModel], val nextAttack: NextAttack, val statModel: StatModel,
  val attackMap: Map[String, SkillModel], val buffModelMap: Map[String, BuffModel], val rotationLogic: StateCheck) {
  val FormulaResult: MutMap[FormulaNames, Double] = new MutMap[FormulaNames, Double]()
  val eventLog: MutArray[String] = new MutArray[String]()
  /*First Key is general type, like affects all, affects physical, affects a specific skill etc,
    Second Key is more specific types like resistance
    The last performed combo skill will also count as a buff and will be first key "State", second key "Last Combo Skill"
   */
  val buffMap: MutMap[BuffMapTypes, MutMap[String, BuffModel]] = new MutMap[BuffMapTypes, MutMap[String, BuffModel]]()
  var potencyResult: Double = 0
  var critResult: Double = 0
  var checkSuccess: Boolean = false



  var timeChange: Double = 0
  var actionName: String = ""
  var snapShotBuffMap: MutMap[BuffMapTypes, MutMap[String, BuffModel]] = new MutMap[BuffMapTypes, MutMap[String, BuffModel]]()
  var time: Double = 0
  for (i <- BuffMapTypes.values) {
    buffMap.put(i, new MutMap[String, BuffModel])
  }

  def cleanResults(): Unit = {
    potencyResult = 0
    critResult = 0
    checkSuccess = false
    timeChange = 0
  }

  def updateTime(time: Double): Unit = {
    //buffMap.transform((_, v) => v.transform((_,v)  =>  updateTime(v, time)))
    buffMap.values.foreach(firstMapValue => {
      for (buffs <- firstMapValue) {
        buffs._2.timeChange(time)
        if (buffs._2.valueMap(BuffValueNames.Time) <= 0) {
          removeBuff(firstMapValue, buffs._1)
        }
      }

    }
    )
    timeChange += time

  }

  //To be fixed soonTM
  private def removeBuff(mapForRemoval: MutMap[String, BuffModel], name: String): Unit = {
    mapForRemoval.remove(name)
    eventLog += (time + ":" + name + " falls off")
  }
}

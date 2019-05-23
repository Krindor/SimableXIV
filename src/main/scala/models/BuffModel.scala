package models

import Core.SimState
import Interfaces.{SkillInterface, SkillModelInterface}

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, HashMap => MutMap}

class BuffModel(val name: String, val  checkArray: ArrayBuffer[(SimState, mutable.Queue[String]) => Unit],
                val queueForCheckArray: ArrayBuffer[mutable.Queue[String]], val damageOverTimePotency: Double = 0) extends SkillModelInterface{

  var buffMap: MutMap[String, MutMap[String, BuffModel]] = new MutMap[String, MutMap[String,BuffModel]]()

  val skillIArray: Array[SkillInterface] = createSkillIArray(name)

  var stringValue:String = _

  val valueMap: MutMap[String, Double] = new MutMap[String, Double]()
  valueMap.put("Time", 0)
  valueMap.put("Stack", 0)

  def timeChange(timeChange: Double): Unit ={
    valueMap.update("Time", valueMap("Time") - timeChange)
  }

  def stackChange(stackChange: Double): Unit ={
    valueMap.update("Stack", valueMap("Stack") + stackChange)
  }

  def addTime (time: Double): Unit  ={
    valueMap.update("Time", time)
  }

  def resetStacks(): Unit ={
    valueMap.update("Stack", 0)
  }



}

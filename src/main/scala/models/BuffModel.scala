package models

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, HashMap => MutMap}

class BuffModel(val damageOverTimePotency: Double = 0, val name: String, val  checkArray: ArrayBuffer[(SimModel, mutable.Queue[String]) => Unit],
                val queueForCheckArray: ArrayBuffer[mutable.Queue[String]]) {
  var buffMap: MutMap[String, MutMap[String, BuffModel]] = new MutMap[String, MutMap[String,BuffModel]]()

  private val pairing = checkArray zip queueForCheckArray


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

  def runAttack(oldSimModel: SimModel): Unit ={

    for ((check, queue) <- pairing){

      check(oldSimModel, queue)
      print(" ran check")
    }

  }

}

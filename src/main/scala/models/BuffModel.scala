package models

import scala.collection.mutable.{HashMap => MutMap}

class BuffModel(val damageOverTimePotency: Double = 0) {
  var buffMap: MutMap[String, MutMap[String, BuffModel]] = new MutMap[String, MutMap[String,BuffModel]]()

  val valueMap: MutMap[String, Double] = new MutMap[String, Double]()
  valueMap.put("Time", 0)
  valueMap.put("Stack", 0)

  def timeChange(timeChange: Double): Unit ={
    valueMap.update("Time", valueMap("Time") - timeChange)
  }

  def stackChange(stackChange: Double): Unit ={
    valueMap.update("Stack", valueMap("Stack") + stackChange)
  }

}

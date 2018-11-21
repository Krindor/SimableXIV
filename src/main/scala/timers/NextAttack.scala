package timers
import models.SimModel

import scala.collection.mutable.{HashMap => MutHashMap}

class NextAttack {
  private val actionMap: MutHashMap[String, SimModel => Unit] = new MutHashMap[String, SimModel => Unit]
  private var timerMap: MutHashMap[String, Double] = new MutHashMap[String, Double]



//Updates the time value for all values
  def updateValue(change:Double):Unit = {
    timerMap = timerMap.transform((_ ,v) => v - change)
  }
//Adds both the time and the connected function into separate maps
  def addFunction(attackFunction: SimModel => Unit, name: String, startValue: Double = 0): Unit ={
    actionMap.update(name, attackFunction)
    timerMap.update(name, startValue)
  }
//Checks for the next attack type and returns the function and the time until next attack
  def getNextAttack:(SimModel => Unit, Double) = {
    val changeValue =  timerMap.valuesIterator.min
    val returningValue = actionMap(timerMap.find(_._2  == changeValue).get._1)
    updateValue(changeValue)
    (returningValue, changeValue)
  }

  def removeFunction(name: String): Unit ={
    actionMap.remove(name)
    timerMap.remove(name)
  }
}

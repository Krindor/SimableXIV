package timers
import models.SimModel

import scala.collection.mutable.{HashMap => MutHashMap}

class NextAttack {
  private var actionMap: MutHashMap[String, SimModel => SimModel] = new MutHashMap[String, SimModel => SimModel]
  private var timerMap: MutHashMap[String, Double] = new MutHashMap[String, Double]


  def updateValue(change:Double):Unit = {
    timerMap = timerMap.transform((k ,v) => v - change)
  }

  def addFunction(attackFunction: SimModel => SimModel, name: String, startValue: Double = 0): Unit ={
    actionMap.update(name, attackFunction)
    timerMap.update(name, startValue)
  }
//Checks for the next attack type and returns the function and the time until next attack
  def getNextAttack:(SimModel => SimModel, Double) = {
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

package timers

import Core.SimState
import Enums.GeneralFunctionNames.GeneralFunctionNames

import scala.collection.mutable.{HashMap => MutHashMap}

class NextAttack {
  private val actionMap: MutHashMap[GeneralFunctionNames, SimState => Unit] = new MutHashMap[GeneralFunctionNames, SimState => Unit]
  private var timerMap: MutHashMap[GeneralFunctionNames, Double] = new MutHashMap[GeneralFunctionNames, Double]

  //Adds both the time and the connected function into separate maps
  def addFunction(attackFunction: SimState => Unit, name: GeneralFunctionNames, startValue: Double = 0): Unit = {
    actionMap.update(name, attackFunction)
    timerMap.update(name, startValue)
  }

  //Checks for the next attack type and returns the function and the time until next attack
  def getNextAttack: (SimState => Unit, Double) = {
    val changeValue = timerMap.valuesIterator.min
    val returningValue = actionMap(timerMap.find(_._2 == changeValue).get._1)
    updateValue(changeValue)
    (returningValue, changeValue)
  }

  //Updates the time value for all values
  def updateValue(change: Double): Unit = {
    timerMap = timerMap.mapValuesInPlace((_, value) => value - change)
  }

  def removeFunction(name: GeneralFunctionNames): Unit = {
    actionMap.remove(name)
    timerMap.remove(name)
  }
}

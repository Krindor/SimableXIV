package models

import Core.SimState
import Enums.BuffMapTypes.BuffMapTypes
import Enums.BuffValueNames
import Enums.BuffValueNames.BuffValueNames
import Interfaces.{SkillInterface, SkillModelInterface}

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, HashMap => MutMap}

class BuffModel(val name: String, val checkArray: ArrayBuffer[(SimState, mutable.Queue[String]) => Unit],
  val queueForCheckArray: ArrayBuffer[mutable.Queue[String]], val damageOverTimePotency: Double = 0) extends SkillModelInterface {
  private val skillI = createSkillI(name)
  val skillIArray: Array[SkillInterface] = Array(skillI)
  val valueMap: MutMap[BuffValueNames, Double] = new MutMap[BuffValueNames, Double]()
  var buffMap: MutMap[BuffMapTypes, MutMap[String, BuffModel]] = new MutMap[BuffMapTypes, MutMap[String, BuffModel]]()
  var stringValue: String = _
  valueMap.put(BuffValueNames.Time, 0)
  valueMap.put(BuffValueNames.Stack, 0)

  def timeChange(timeChange: Double): Unit = {
    valueMap.update(BuffValueNames.Time, valueMap(BuffValueNames.Time) - timeChange)
  }

  def stackChange(stackChange: Double): Unit = {
    valueMap.update(BuffValueNames.Stack, valueMap(BuffValueNames.Stack) + stackChange)
  }

  def addTime(time: Double): Unit = {
    valueMap.update(BuffValueNames.Time, time)
  }

  def resetStacks(): Unit = {
    valueMap.update(BuffValueNames.Stack, 0)
  }

  def run(simState: SimState): Unit = {

    runAttack(simState, skillIArray)

  }


}

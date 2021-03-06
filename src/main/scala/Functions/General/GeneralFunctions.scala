package Functions.General

import Core.SimState
import Enums.GeneralFunctionNames.GeneralFunctionNames
import Enums.{BuffMapTypes, GeneralFunctionNames}
import models.OpenerModel

import scala.collection.mutable.{HashMap => mutHashMap}

object GeneralFunctions {

  val hashMap: mutHashMap[GeneralFunctionNames, SimState => Unit] = new mutHashMap[GeneralFunctionNames, SimState => Unit]
  hashMap.put(GeneralFunctionNames.Start, attackStart)
  hashMap.put(GeneralFunctionNames.End, attackEnd)
  hashMap.put(GeneralFunctionNames.DamageOverTime, damageOverTime)
  hashMap.put(GeneralFunctionNames.Opener, runOpener)
  hashMap.put(GeneralFunctionNames.ActionPicker, actionPicker)
  hashMap.put(GeneralFunctionNames.ApplyAttack, applyAttack)
  hashMap.put(GeneralFunctionNames.TimeChange, changeTime)

  def getFunction(functionName: GeneralFunctionNames): SimState => Unit = hashMap(functionName)

  //Initiates an attack
  def attackStart(simState: SimState): Unit = {
    simState.nextAttack.addFunction(attackEnd, GeneralFunctionNames.End, simState.attackMap(simState.actionName).castTime)
    simState.nextAttack.removeFunction(GeneralFunctionNames.Start)
    simState.eventLog += (simState.time + ":Starts casting " + simState.actionName)

  }

  //When the cast/attack actually finishes
  def attackEnd(simState: SimState): Unit = {
    simState.nextAttack.addFunction(applyAttack, GeneralFunctionNames.ApplyAttack, simState.attackMap(simState.actionName).applicationOffset)
    simState.snapShotBuffMap = simState.buffMap
    simState.nextAttack.removeFunction(GeneralFunctionNames.End)
    simState.eventLog += (simState.time + ":Finishes casting " + simState.actionName)

  }

  def damageOverTime(simState: SimState): Unit = {
    simState.buffMap(BuffMapTypes.DamageOverTime).values.foreach(value => value.run(simState))
  }

  def actionPicker(simState: SimState): Unit = {
    //runs the check and returns the name of the action, or none if it didn't find any that matches the current state
    simState.actionName = simState.rotationLogic.check(simState)._2

  }

  //When the buffs actually apply will also calculate the damage here
  def applyAttack(simState: SimState): Unit = {

    simState.attackMap(simState.actionName).run(simState)
    simState.nextAttack.removeFunction(GeneralFunctionNames.ApplyAttack)
    simState.cleanResults()

  }

  def runOpener(simState: SimState): Unit = {
    //removes the first element in the queue containing the opener order

    val openerModel: OpenerModel = simState.openerQueue.dequeue()

    val attack = simState.attackMap(openerModel.skillName)
    attack.run(simState)
    //removes the opener and adds more specific types to handle the rotation
    if (simState.openerQueue.isEmpty) {
      simState.nextAttack.removeFunction(GeneralFunctionNames.Opener)
      for (i <- GeneralFunctionNames.values) {
        if (!i.equals(GeneralFunctionNames.Opener)) {
          simState.nextAttack.addFunction(GeneralFunctions.getFunction(i), i)
        }
      }
    }

  }

  def changeTime(simModel: SimState): Unit = {
    simModel.updateTime(simModel.timeChange)
  }

  //I don't even know why this is here
  /*def applyCritDamage(simModel: SimState): (Double, Double) = {
    val critResult: (Double, Double) = simModel.formulaMap("Crit")(simModel, 0)

    critResult._2 match {
      case 0 => (1, critResult._2)
      case 1 => (critResult._1 + 1.45, critResult._2)
      case 2 => (1 + ((critResult._1 + 0.05) * (critResult._1 + 0.45)), critResult._2)
    }
  }

   */
}

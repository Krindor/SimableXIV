package Functions.General

import Interfaces.FuncInterface
import models.{OpenerModel, SimModel}

import scala.collection.mutable.{HashMap => mutHashMap}

class GeneralFunctions extends FuncInterface {
  //Initiates and attack
  def attackStart(simModel: SimModel): Unit ={
    simModel.nextAttack.addFunction(simModel.generalFunctionMap("End"), "End", simModel.attackMap(simModel.actionName).castTime)
    simModel.nextAttack.removeFunction("Start")
  }
  //When the cast/attack actually finishes
  def attackEnd(simModel: SimModel): Unit ={
    simModel.nextAttack.addFunction(simModel.generalFunctionMap("ApplyAttack"), "ApplyAttack",simModel.attackMap(simModel.actionName).applicationOffset)
    simModel.snapShotBuffMap = simModel.buffMap
    simModel.nextAttack.removeFunction("End")
    simModel.generalFunctionMap("TimeChange")(simModel)
  }

  def damageOverTime(simModel: SimModel): Unit ={
    for (dot <- simModel.buffMap("DamageOverTime")){
      //deal damage
    }
  }

  def actionPicker(simModel: SimModel): Unit ={
    //runs the check and returns the name of the action, or none if it didn't find any that matches the current state
    simModel.actionName = simModel.rotationLogic.check(simModel)._2

  }
  //When the buffs actually apply(Fuck Square) will also calculate the damage here
  def applyAttack(simModel: SimModel): Unit ={

    simModel.attackMap(simModel.actionName).runAttack(simModel)
    simModel.nextAttack.removeFunction("ApplyAttack")


  }

  def runOpener(simModel: SimModel): Unit = {
    //removes the first element in the queue containing the opener order


    val openerModel: OpenerModel = simModel.openerQueue.dequeue()

    val attack = simModel.attackMap(openerModel.skillName)
    attack.runAttack(simModel)
    //removes the opener and adds more specific types to handle the rotation
    if (simModel.openerQueue.isEmpty) {
      simModel.nextAttack.removeFunction("Opener")
      for (i <- simModel.generalFunctionMap) {
        simModel.nextAttack.addFunction(i._2, i._1)
      }
    }

  }

  def changeTime(simModel:SimModel): Unit ={
    simModel.updateTime(simModel.timeChange)

  }

  def getFunctions: mutHashMap[String, SimModel => Unit] ={
    val hashMap: mutHashMap[String, SimModel => Unit] = new mutHashMap[String, SimModel => Unit]
    hashMap.put("Start", attackStart)
    hashMap.put("End", attackEnd)
    hashMap.put("DamageOverTime", damageOverTime)
    hashMap.put("Opener", runOpener)
    hashMap.put("ActionPicker", actionPicker)
    hashMap.put("ApplyAttack", applyAttack)
    hashMap
  }
//I don't even know why this is here
  def applyCritDamage(simModel: SimModel): (Double, Double)={
    val critResult: (Double, Double) = simModel.formulaMap("Crit")(simModel, 0)

    critResult._2 match {
      case 0 => (1, critResult._2)
      case 1 => (critResult._1+1.45, critResult._2)
      case 2 => (1+((critResult._1 + 0.05) * (critResult._1 + 0.45)), critResult._2)
    }
  }
}

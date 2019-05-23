package Functions.General

import Core.SimState
import Interfaces.AttackFuncInterface
import models.BuffModel

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, Queue => MutQueue}
//Most classes here will be changed later to allow less checks to be performed once
class GeneralAttackFunctions extends AttackFuncInterface{


  //check if the state matches the condition for it to fire
  def stateCheck(simModel: SimState, oldMutQueue: MutQueue[String]): Unit ={
    val mutQueue = oldMutQueue
    val arraySize = 2
    var stringArray: ArrayBuffer[String] = new ArrayBuffer[String](arraySize)
    /*
    Array(0) = type of state, could also be "Last Attack"
    Array(1) = function to execute
    Array(2) = name of function to execute if first check fails
     */

    for (_ <- 0 to arraySize){
      stringArray += mutQueue.dequeue()
    }
    print(" Done with Loop")
    match {
      case _ if simModel.buffMap("Solo")("Last Action").stringValue == stringArray(0)=>
        simModel.checkSuccess=true
        simModel.attackFunctionMap(stringArray(1))(simModel, mutQueue)

      case _ if stringArray(3)!= "None" =>
        simModel.checkSuccess=false
        simModel.attackFunctionMap(stringArray(2))(simModel, mutQueue)
      case _ =>
      }

    print(" Done with match")
  }
  /*
  Queue(0) = If it was called from a check type function or not
  Queue(1) = Success Potency/Normal potency
  Queue(2) = Fail Potency
   */
  def applyPotency(simModel: SimState, oldMutQueue: MutQueue[String]): Unit ={

    oldMutQueue.head match {
      case "Check" =>
        if (simModel.checkSuccess) simModel.potencyResult = oldMutQueue(1).toDouble

        else simModel.potencyResult = oldMutQueue(2).toDouble
      case _ => simModel.potencyResult = oldMutQueue(1).toDouble
    }

  }
  /*
  Queue(0) = general buff type
  Queue(1) = target buff name
   */
  def addBuff(simModel: SimState, mutQueue: MutQueue[String]): Unit ={
    val oldMutQueue = mutQueue
    simModel.buffMap(oldMutQueue.dequeue()).put(oldMutQueue.head, simModel.buffModelMap(oldMutQueue.dequeue()))

  }
  /*
  Queue(0) = DoT Name
   */
  def addDoT(simModel: SimState, mutQueue: MutQueue[String]): Unit = {
    val oldMutQueue = mutQueue
    val dotName = oldMutQueue.dequeue()
    val buffModel: BuffModel = simModel.buffModelMap(dotName)
    buffModel.buffMap = simModel.buffMap
    simModel.buffMap("DamageOverTime").put(oldMutQueue.head, simModel.buffModelMap(dotName))
  }

  def removeBuff(oldSimModel: SimState, mutQueue: MutQueue[String]): Unit ={
    val oldMutQueue = mutQueue
    oldSimModel.buffMap(oldMutQueue.dequeue()).remove(oldMutQueue.dequeue())

  }
  /*
  Queue(0) = action name
   */
  def updateLastAction(simModel: SimState, mutQueue: MutQueue[String]): Unit ={
    val oldMutQueue = mutQueue
    val buffModel:BuffModel = simModel.buffModelMap("Last Action")
    buffModel.stringValue = oldMutQueue.dequeue()
    simModel.buffMap("Solo").put("Last Action", buffModel)

  }



  def getAttackFunctions: mutable.HashMap[String, (SimState, mutable.Queue[String]) => Unit] ={
    val mutMap: mutable.HashMap[String, (SimState, mutable.Queue[String]) => Unit] = new mutable.HashMap[String, (SimState, mutable.Queue[String]) => Unit]
    mutMap.put("State Check", stateCheck)
    mutMap.put("Add Buff", addBuff)
    mutMap.put("Remove Buff", removeBuff)
    mutMap.put("Add DoT", addDoT)
    mutMap.put("Last Action", updateLastAction)
    mutMap
  }


}

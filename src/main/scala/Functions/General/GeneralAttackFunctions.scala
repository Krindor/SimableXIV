package Functions.General

import Interfaces.AttackFuncInterface
import models.SimModel

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, Queue => MutQueue}

class GeneralAttackFunctions extends AttackFuncInterface{

  def stateCheck(simModel: SimModel, oldMutQueue: MutQueue[String]): Unit ={
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
      case _ if simModel.buffMap("Solo").contains(stringArray(0))=>
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
  def applyPotency(simModel: SimModel, oldMutQueue: MutQueue[String]): Unit ={

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
  def addBuff(SimModel: SimModel, mutQueue: MutQueue[String]): Unit ={
    val oldMutQueue = mutQueue
    SimModel.buffMap(oldMutQueue.dequeue()).put(oldMutQueue.head, SimModel.buffModelMap(oldMutQueue.dequeue()))

  }

  def removeBuff(oldSimModel: SimModel, mutQueue: MutQueue[String]): Unit ={
    val oldMutQueue = mutQueue
    oldSimModel.buffMap(oldMutQueue.dequeue()).remove(oldMutQueue.dequeue())

  }



  def getAttackFunctions: mutable.HashMap[String, (SimModel, mutable.Queue[String]) => Unit] ={
    val mutMap: mutable.HashMap[String, (SimModel, mutable.Queue[String]) => Unit] = new mutable.HashMap[String, (SimModel, mutable.Queue[String]) => Unit]
    mutMap.put("State Check", stateCheck)
    mutMap.put("Add Buff", addBuff)
    mutMap.put("Remove Buff", removeBuff)
    mutMap
  }


}

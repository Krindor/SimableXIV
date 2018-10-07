package Functions.General

import Interfaces.AttackFuncInterface
import models.SimModel

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, Queue => MutQueue}

class GeneralAttackFunctions extends AttackFuncInterface{

  def stateCheck(oldSimModel: SimModel, oldMutQueue: MutQueue[String]): SimModel ={
    var simModel = oldSimModel
    val arraySize = 3
    var stringArray: ArrayBuffer[String] = new ArrayBuffer[String](arraySize)
    /*
    Array(0) = name of state to find
    Array(1) = type of state, could also be "Last Attack"
    Array(2) = function to execute
    Array(3) = name of function to execute if first check fails
     */


    for (i <- 0 to arraySize){

      stringArray += oldMutQueue.dequeue()
    }
    print(" Done with Loop")
    simModel = stringArray(0) match {
      case x if simModel.soloBuffs("State")(stringArray(1)).contains(x)=>
        simModel.checkSuccess=true
        simModel.attackFunctionMap(stringArray(2))(simModel, oldMutQueue)

      case x if stringArray(3)!= "None" =>
        simModel.checkSuccess=false
        simModel.attackFunctionMap(stringArray(3))(simModel, oldMutQueue)

      case _ => simModel
      }
    print(" Done with match")
    simModel

  }
  /*
  Queue(0) = If it was called from a check type function or not
  Queue(1) = Success Potency/Normal potency
  Queue(2) = Fail Potency
   */
  def applyPotency(oldSimModel: SimModel, oldMutQueue: MutQueue[String]): SimModel ={
    var simModel = oldSimModel
    oldMutQueue.head match {
      case "Check" =>
        if (simModel.checkSuccess) simModel.potencyResult = oldMutQueue(1).toDouble

        else simModel.potencyResult = oldMutQueue(2).toDouble
      case _ => simModel.potencyResult = oldMutQueue(1).toDouble
    }
    simModel
  }
  /*
  Queue(0) = general buff type
  Queue(1) = specific buff type
  Queue(2) = target buff name
   */
  def addBuff(oldSimModel: SimModel, oldMutQueue: MutQueue[String]): SimModel ={
    oldSimModel.soloBuffs(oldMutQueue.dequeue())(oldMutQueue.dequeue()).put(oldMutQueue.head, oldSimModel.buffMap(oldMutQueue.dequeue()))
    oldSimModel
  }

  def removeBuff(oldSimModel: SimModel, oldMutQueue: MutQueue[String]): SimModel ={
    oldSimModel.soloBuffs(oldMutQueue.dequeue())(oldMutQueue.dequeue()).remove(oldMutQueue.dequeue())
    oldSimModel
  }



  def getAttackFunctions: mutable.HashMap[String, (SimModel, mutable.Queue[String]) => SimModel] ={
    val mutMap: mutable.HashMap[String, (SimModel, mutable.Queue[String]) => SimModel] = new mutable.HashMap[String, (SimModel, mutable.Queue[String]) => SimModel]
    mutMap.put("State Check", stateCheck)
    mutMap.put("Add Buff", addBuff)
    mutMap.put("Remove Buff", removeBuff)
    mutMap
  }


}

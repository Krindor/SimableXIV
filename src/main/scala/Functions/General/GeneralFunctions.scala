package Functions.General

import Interfaces.FuncInterface
import models.{OpenerModel, SimModel}

import scala.collection.mutable.{HashMap => mutHashMap}

class GeneralFunctions extends FuncInterface {
  def attackStart(oldSimModel: SimModel): Unit ={
    var simModel:SimModel = oldSimModel
    simModel
  }

  def damageOverTime(oldSimModel: SimModel): Unit ={
    var simModel:SimModel = oldSimModel
    simModel
  }

  def attackEnd(oldSimModel: SimModel): Unit ={
    var simModel:SimModel = oldSimModel
    simModel
  }

  def runOpener(simModel: SimModel): Unit = {
    //removes the first element in the queue containing the opener order


    val openerModel: OpenerModel = simModel.openerQueue.dequeue()

    val attack = simModel.attackMap(openerModel.skillName)
    attack.runAttack(simModel)
    //removes the opener and adds more specific types to handle the rotation
    if (simModel.openerQueue.isEmpty) {
      simModel.nextAttack.removeFunction("Opener")
      for (i <- simModel.attackTypeMap) {
        simModel.nextAttack.addFunction(i._2, i._1)
      }
    }

  }

  def changeTime(oldSimModel:SimModel): Unit ={
    oldSimModel.updateTime(oldSimModel.timeChange)

  }

  def getFunctions: mutHashMap[String, SimModel => Unit] ={
    val hashMap: mutHashMap[String, SimModel => Unit] = new mutHashMap[String, SimModel => Unit]
    hashMap.put("Start", attackStart)
    hashMap.put("End", attackEnd)
    hashMap.put("DamageOverTime", damageOverTime)
    hashMap.put("Opener", runOpener)
    hashMap
  }

  def applyCritDamage(simModel: SimModel): (Double, Double)={
    val critResult: (Double, Double) = simModel.formulaMap("Crit")(simModel, 0)

    critResult._2 match {
      case 0 => (1, critResult._2)
      case 1 => (critResult._1+1.45, critResult._2)
      case 2 => (1+((critResult._1 + 0.05) * (critResult._1 + 0.45)), critResult._2)
    }
  }
}

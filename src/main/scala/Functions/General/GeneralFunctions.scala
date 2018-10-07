package Functions.General

import Interfaces.FuncInterface
import models.{OpenerModel, SimModel}

import scala.collection.mutable.{HashMap => mutHashMap}

class GeneralFunctions extends FuncInterface {
  def attackStart(oldSimModel: SimModel): SimModel ={
    var simModel:SimModel = oldSimModel
    simModel
  }

  def damageOverTime(oldSimModel: SimModel): SimModel ={
    var simModel:SimModel = oldSimModel
    simModel
  }

  def attackEnd(oldSimModel: SimModel): SimModel ={
    var simModel:SimModel = oldSimModel
    simModel
  }

  def runOpener(oldSimModel: SimModel): SimModel = {
    //removes the first element in the queue containing the opener order
    var simModel:SimModel = oldSimModel

    val openerModel: OpenerModel = simModel.openerQueue.dequeue()

    val attack = simModel.attackMap(openerModel.skillName)
    simModel = attack.runAttack(simModel)
    //removes the opener and adds more specific types to handle the rotation
    if (simModel.openerQueue.isEmpty) {
      simModel.nextAttack.removeFunction("Opener")
      for (i <- simModel.attackTypeMap) {
        simModel.nextAttack.addFunction(i._2, i._1)
      }
    }
    simModel
  }

  def getFunctions: mutHashMap[String, SimModel => SimModel] ={
    val hashMap: mutHashMap[String, SimModel => SimModel] = new mutHashMap[String, SimModel => SimModel]
    hashMap.put("Start", attackStart)
    hashMap.put("End", attackEnd)
    hashMap.put("DamageOverTime", damageOverTime)
    hashMap.put("Opener", runOpener)
    hashMap
  }

  def applyCritDamage(simModel: SimModel): (Double, Double)={
    val critResult: (Double, Double) = simModel.formulaMap("Crit")(simModel)

    critResult._2 match {
      case 0 => (1, critResult._2)
      case 1 => (critResult._1+1.45, critResult._2)
      case 2 => (1+((critResult._1 + 0.05) * (critResult._1 + 0.45)), critResult._2)
    }
  }
}

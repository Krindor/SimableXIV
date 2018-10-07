package models

import scala.collection.mutable

class SkillModel(val skillName: String, val skillType: String, val  checkArray: Array[(SimModel, mutable.Queue[String]) => SimModel], val queueForCheckArray: Array[mutable.Queue[String]]) {
  private val pairing = checkArray zip queueForCheckArray

  def runAttack(simModel: SimModel): SimModel ={
    var simModel = simModel
    for ((check, queue) <- pairing){
      simModel = check(simModel, queue)
    }

    simModel
  }
}

package models
import timers.NextAttack


import scala.collection.mutable
import scala.collection.mutable.{HashMap => MutMap, Queue => MutQueue}
//Contains variables handled in the core to ease passing them
class SimModel(var openerQueue: MutQueue[OpenerModel], var nextAttack: NextAttack, val attackTypeMap: MutMap[String, SimModel => SimModel], val formulaMap: MutMap[String, SimModel => (Double, Double)], var statModel: StatModel,
               val attackMap: MutMap[String, SkillModel], val attackFunctionMap: MutMap[String, (SimModel, mutable.Queue[String]) => SimModel], val buffMap: MutMap[String, BuffModel]) {
  var potencyResult: Double = 0
  var critResult: Double = 0
  var checkSuccess: Boolean = false
  /*First Key is general type, like affects all, affects physical, affects a specific skill etc,
    Second Key is more specific types like resistance
    The last performed skill will also count as a buff and will be first key "State", second key "Last Action"
   */
  var soloBuffs: MutMap[String, MutMap[String, MutMap[String, BuffModel]]] = new MutMap[String, MutMap[String, MutMap[String, BuffModel]]]

  def cleanResults(): Unit = {
    potencyResult = 0
    critResult = 0
    checkSuccess = false
  }
}

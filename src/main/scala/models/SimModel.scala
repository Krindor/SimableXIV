package models
import timers.NextAttack

import scala.collection.immutable.HashMap
import scala.collection.mutable.{Queue => MutQueue}
//Contains variables handled in the core to ease passing them
class SimModel(var openerQueue: MutQueue[OpenerModel], var nextAttack: NextAttack, val attackTypeMap: HashMap[String, SimModel => SimModel], val formulaMap: HashMap[String, SimModel => (Double, Double)], var statModel: StatModel, val attackMap: HashMap[String, SkillModel]) {
  var resultingDamage: Int = 0


}

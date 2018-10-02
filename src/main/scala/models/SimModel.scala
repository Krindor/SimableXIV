package models
import timers.NextAttack

import scala.collection.immutable.HashMap
import scala.collection.mutable.Queue
//Contains variables handled in the core to ease passing them
class SimModel(var openerQueue: Queue[OpenerModel], var nextAttack: NextAttack, val attackTypeMap: HashMap[String, SimModel => SimModel]) {

}

package models
import timers.NextAttack


import scala.collection.mutable
import scala.collection.mutable.{HashMap => MutMap, Queue => MutQueue}
//Contains variables handled in the core to ease passing them
class SimModel(var openerQueue: MutQueue[OpenerModel], var nextAttack: NextAttack, val attackTypeMap: MutMap[String, SimModel => Unit], val formulaMap: MutMap[String, (SimModel, Double )=> (Double, Double)], var statModel: StatModel,
               val attackMap: MutMap[String, SkillModel], val attackFunctionMap: MutMap[String, (SimModel, mutable.Queue[String]) => Unit], val buffMap: MutMap[String, BuffModel]) {
  var potencyResult: Double = 0
  var critResult: Double = 0
  var checkSuccess: Boolean = false
  val speedFormulaResult: MutMap[String, Double] = new MutMap[String, Double]()
  var damageFormulaResult: Double = 0
  var critFormulaResult: Double = 0
  var dhFormulaResult: Double = 0
  var timeChange: Double = 0
  /*First Key is general type, like affects all, affects physical, affects a specific skill etc,
    Second Key is more specific types like resistance
    The last performed skill will also count as a buff and will be first key "State", second key "Last Action"
   */
  var soloBuffs: MutMap[String, MutMap[String, MutMap[String, BuffModel]]] = new MutMap[String, MutMap[String, MutMap[String, BuffModel]]]
  var partyBuffs: MutMap[String, MutMap[String, MutMap[String, BuffModel]]] = new MutMap[String, MutMap[String, MutMap[String, BuffModel]]]
  var targetBuffs: MutMap[String, MutMap[String, MutMap[String, BuffModel]]] = new MutMap[String, MutMap[String, MutMap[String, BuffModel]]]
  var cooldowns: MutMap[String, MutMap[String, MutMap[String, BuffModel]]] = new MutMap[String, MutMap[String, MutMap[String, BuffModel]]]

  def cleanResults(): Unit = {
    potencyResult = 0
    critResult = 0
    checkSuccess = false
    timeChange = 0
  }

  def updateTime(buffModel: BuffModel, double: Double): BuffModel ={
    buffModel.timeChange(double)
    buffModel
  }

  def updateTime(time: Double): Unit ={
    soloBuffs.transform((_, v) => v.transform((_,v) => v.transform((_,v) => updateTime(v, time))))
    partyBuffs.transform((_, v) => v.transform((_,v) => v.transform((_,v) => updateTime(v, time))))
    targetBuffs.transform((_, v) => v.transform((_,v) => v.transform((_,v) => updateTime(v, time))))
    cooldowns.transform((_, v) => v.transform((_,v) => v.transform((_,v) => updateTime(v, time))))
  }
}

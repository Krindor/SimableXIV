package Interfaces

import Core.SimState

import scala.collection.mutable

trait AttackFuncInterface {
  def getAttackFunctions: mutable.HashMap[String, (SimState, mutable.Queue[String]) => Unit]
}

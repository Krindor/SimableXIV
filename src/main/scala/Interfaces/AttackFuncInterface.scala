package Interfaces

import models.SimModel

import scala.collection.mutable

trait AttackFuncInterface {
  def getAttackFunctions: mutable.HashMap[String, (SimModel, mutable.Queue[String]) => Unit]
}

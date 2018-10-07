package Interfaces

import models.SimModel

import scala.collection.mutable.{HashMap => MutMap}

trait FuncInterface {
  def getFunctions: MutMap[String, SimModel => SimModel]

}

package Interfaces

import Core.SimState

import scala.collection.mutable.{HashMap => MutMap}

trait FuncInterface {
  def getFunctions: MutMap[String, SimState => Unit]

}

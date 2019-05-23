package Interfaces

import Core.SimState

import scala.collection.mutable

trait FormulaInterface {
  def getFormulas: mutable.HashMap[String, (SimState, Double) => (Double, Double)]

}

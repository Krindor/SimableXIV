package Interfaces

import models.SimModel

import scala.collection.mutable

trait FormulaInterface {
  def getFormulas: mutable.HashMap[String, SimModel => (Double, Double)]

}

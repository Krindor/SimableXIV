package models.RotationLogic

import models.SimModel


class ConditionLogic(array: Array[String]) {
  private val targetName = array(0)
  private val comparer = array(1).toCharArray
  private val comparingValue = array(2).toDouble
  private val targetMap = array(3)
  private val targetValue = array(4)

  private var logic: (Double, SimModel, Double) => Boolean = _
    comparer(1) match {
      case '>' => logic = moreThan
      case '=' => logic = equalTo
      case '<' => logic = lessThan
      case '!' => logic = exists
    }



  def check(simModel: SimModel): Boolean= {
    val value = simModel.buffMap(targetMap)(targetName).valueMap(targetValue)
    logic(value, simModel, comparingValue)
  }

  def moreThan(value:Double, simModel: SimModel, comparingValue:Double): Boolean = { if (value > comparingValue) true else false}

  def equalTo(value:Double, simModel: SimModel, comparingValue:Double): Boolean = { if (value.equals(comparingValue)) true else false}

  def lessThan(value:Double, simModel: SimModel, comparingValue:Double): Boolean = { if (value < comparingValue) true else false}

  def exists(value:Double, simModel: SimModel, comparingValue:Double): Boolean = {
    if (comparingValue == 0 && value == 0) true
    else if (comparingValue == 1 && value >= 1) true
    else false
  }

}



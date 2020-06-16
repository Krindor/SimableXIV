package models.RotationLogic

import Core.SimState
import Enums.{BuffMapTypes, BuffValueNames}


class ConditionLogic(array: Array[String]) {
  private val targetName = array(0)
  private val comparer = array(1).toCharArray
  private val comparingValue = array(2).toDouble
  private val targetMap = BuffMapTypes.withName(array(3))
  private val targetValue = BuffValueNames.withName(array(4))

  private var logic: (Double, SimState, Double) => Boolean = _
  comparer(1) match {
    case '>' => logic = moreThan
    case '=' => logic = equalTo
    case '<' => logic = lessThan
    case '!' => logic = exists
  }


  def check(simModel: SimState): Boolean = {
    val value = simModel.buffMap(targetMap)(targetName).valueMap(targetValue)
    logic(value, simModel, comparingValue)
  }

  def moreThan(value: Double, simModel: SimState, comparingValue: Double): Boolean = {
    if (value > comparingValue) true else false
  }

  def equalTo(value: Double, simModel: SimState, comparingValue: Double): Boolean = {
    if (value.equals(comparingValue)) true else false
  }

  def lessThan(value: Double, simModel: SimState, comparingValue: Double): Boolean = {
    if (value < comparingValue) true else false
  }

  def exists(value: Double, simModel: SimState, comparingValue: Double): Boolean = {
    if (comparingValue == 0 && value == 0) true
    else if (comparingValue == 1 && value >= 1) true
    else false
  }

}



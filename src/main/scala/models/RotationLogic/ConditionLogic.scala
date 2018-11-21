package models.RotationLogic

import models.SimModel

class ConditionLogic(array: Array[String]){
  private val targetName = array(0)
  private val comparer = array(1)
  private val comparingValue = array(2).toDouble
  private val targetMap = array(3)
  private val targetValue = array(4)


/*
  Will rewrite this later to make it more clean and efficient
 */
  def check(simModel: SimModel): Boolean= {
    val value = simModel.buffMap(targetMap)(targetName).valueMap(targetValue)
    var boolean: Boolean = false
    //The loop and match will only be performed once when I clean the code up
    for (comparerChar <- comparer) {
    comparerChar.toString match {
      case ">" => if (value > comparingValue) true else boolean = false
      case "=" => if (value.equals(comparingValue)) true else boolean = false
      case "<" => if (value < comparingValue) true else boolean = false
      case "exist" =>
        if (comparingValue == 0 && value == 0) true
        else if (comparingValue == 1 && value >= 1) true
        else boolean = false
    }
  }
    boolean
  }
}

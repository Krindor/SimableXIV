package models.RotationLogic

import Core.SimState
import Interfaces.RotationLogicInterface

//Checks if the action matches the current state
class ActionCheck(actionName: String, array: Array[ConditionLogic]) extends RotationLogicInterface{
  def check(simModel: SimState): (Boolean, String) ={
    for (condition <- array){
      if (!condition.check(simModel)) return (false, actionName)
    }
    (true, actionName)
  }
}

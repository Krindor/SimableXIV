package models.RotationLogic

import Interfaces.RotationLogicInterface
import models.SimModel

//Checks if the action matches the current state
class ActionCheck(actionName: String, array: Array[ConditionLogic]) extends RotationLogicInterface{
  def check(simModel: SimModel): (Boolean, String) ={
    for (condition <- array){
      if (!condition.check(simModel)) return (false, actionName)
    }
    (true, actionName)
  }
}

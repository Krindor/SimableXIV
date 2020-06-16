package models.RotationLogic

import Core.SimState
import Interfaces.RotationLogicInterface

//Checks states and then checks if there are any states/actions that matches further requirements
class StateCheck(array: Array[(ConditionLogic, RotationLogicInterface)]) extends RotationLogicInterface {
  def check(simModel: SimState): (Boolean, String) = {
    for (stateCheck <- array) {
      if (stateCheck._1.check(simModel)) {
        val check = stateCheck._2.check(simModel)
        if (check._1) return check
      }
    }
    (false, "none")
  }
}

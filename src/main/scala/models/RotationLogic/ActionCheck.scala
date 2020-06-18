package models.RotationLogic

import Core.SimState
import Interfaces.RotationLogicInterface

//Checks if the action matches the current state
case class ActionCheck(actionName: String) extends RotationLogicInterface {
  def check(simState: SimState): (Boolean, String) = {
    (simState.attackMap(actionName).check(simState), actionName)
  }
}

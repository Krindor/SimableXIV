package models.RotationLogic

import Core.SimState
import Interfaces.RotationLogicInterface

/**
 *Last part of the rotation logic. Find the target action and executes the check of the action
 * to see if the action is able to be executed.
 */

case class ActionCheck(actionName: String) extends RotationLogicInterface {
  def check(simState: SimState): (Boolean, String) = {
    (simState.attackMap(actionName).check(simState), actionName)
  }
}

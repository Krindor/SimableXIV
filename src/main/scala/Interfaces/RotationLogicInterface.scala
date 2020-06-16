package Interfaces

import Core.SimState

trait RotationLogicInterface {
  //Returns if the check if true and also the name of the action to be performed if true
  def check(simModel: SimState): (Boolean, String)
}

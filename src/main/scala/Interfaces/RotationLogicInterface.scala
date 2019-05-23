package Interfaces

import Core.SimState

trait RotationLogicInterface {
  def check(simModel: SimState): (Boolean, String)
}

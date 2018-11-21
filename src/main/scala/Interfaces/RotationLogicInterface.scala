package Interfaces

import models.SimModel

trait RotationLogicInterface {
  def check(simModel: SimModel): (Boolean, String)
}

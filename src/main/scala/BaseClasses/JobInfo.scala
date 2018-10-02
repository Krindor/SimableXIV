package BaseClasses

import models.{SimModel, StatModel}

class JobInfo(val statModel: StatModel, val jobName: String, val time:Int) {
  private var simModel: SimModel = new SimModel()

  def getSimModel(): SimModel = simModel
}

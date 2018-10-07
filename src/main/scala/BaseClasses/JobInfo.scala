package BaseClasses

import models.{SimModel, StatModel}

class JobInfo(val statModel: StatModel, val jobName: String, val time:Int) {
  var simModel: SimModel = new SimModel()


}

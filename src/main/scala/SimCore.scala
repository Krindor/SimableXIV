import BaseClasses.JobInfo
import models.{OpenerModel, SimModel, StatModel}
import timers.NextAttack

/*
 * The core part of the library that handles the actual simulations.
 */
class SimCore(val jobInfo: JobInfo) {

  private val time: Double = jobInfo.time
  private var currentTime: Double = 0
  private val nextAttack:NextAttack = new NextAttack
  private var openerArray: Array[String] = new Array[String](30)
  private var simModel: SimModel = jobInfo.getSimModel()

  nextAttack.addFunction(testAttack , "Test", 1)
  //Runs the sim
  def nextAction(damageLog: Array[String]): Array[String] = {
//this loop will be removed later on to allow better customization with the time
    while (currentTime <= time){
      //get the next type of attack from the list and also time until next action
      val attackTuple: (SimModel => SimModel, Double) = nextAttack.getNextAttack
      currentTime = currentTime + attackTuple._2
      //Runs the target attack type and returns the values and states saved in the simModel variable
       simModel = attackTuple._1(simModel)
    }
    damageLog
  }

  def testAttack(simModel: SimModel): SimModel = {
    simModel
  }



  def runOpener(simModel: SimModel): SimModel = {
    //removes the first element in the queue containing the opener order
    var openerModel: OpenerModel = simModel.openerQueue.dequeue()
    //removes the opener and adds more specific types to handle the rotation
    if (simModel.openerQueue.isEmpty) {
      simModel.nextAttack.removeFunction("Opener")
      for (i <- simModel.attackTypeMap) {
        simModel.nextAttack.addFunction(i._2, i._1)
      }
    }
    simModel
  }
}

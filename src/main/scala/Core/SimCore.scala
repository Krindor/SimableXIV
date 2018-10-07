package Core

import BaseClasses.JobInfo
import models.SimModel
import timers.NextAttack

/*
 * The core part of the library that handles the actual simulations.
 */
class SimCore(val jobInfo: JobInfo) {


  private var currentTime: Double = 0
  private val nextAttack:NextAttack = new NextAttack
  private var simModel: SimModel = jobInfo.simModel

  nextAttack.addFunction(simModel.attackTypeMap("Opener") , "Test", 1)

  //Runs the sim
  def nextAction(damageLog: Array[String]): Array[String] = {


      //get the next type of attack from the list and also time until next action
      val attackTuple: (SimModel => SimModel, Double) = nextAttack.getNextAttack
      currentTime = currentTime + attackTuple._2
      //Runs the target attack type and returns the values and states saved in the simModel variable
       simModel = attackTuple._1(simModel)

    damageLog
  }





}

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

  simModel.speedFormulaResult.put("Skill Speed", simModel.formulaMap("Skill Speed")(simModel, 0)._1)
  simModel.speedFormulaResult.put("Spell Speed", simModel.formulaMap("Spell Speed")(simModel, 0)._1)
  simModel.damageFormulaResult = simModel.formulaMap("Damage")(simModel, 0)._1
  simModel.critResult = simModel.formulaMap("Crit")(simModel, 0)._1
  simModel.dhFormulaResult = simModel.formulaMap("Direct Hit")(simModel, 0)._1
  nextAttack.addFunction(simModel.attackTypeMap("Opener") , "Test", 1)

  //Runs the sim
  def nextAction(damageLog: Array[String]): Array[String] = {


      //get the next type of attack from the list and also time until next action
      val attackTuple: (SimModel => Unit, Double) = nextAttack.getNextAttack
      currentTime = currentTime + attackTuple._2
      attackTuple._1(simModel)
      //Runs the target attack type and returns the values and states saved in the simModel variable


    damageLog
  }





}

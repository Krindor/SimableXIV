package Core

import BaseClasses.JobInfo
import Enums.{FormulaNames, GeneralFunctionNames}
import timers.NextAttack
import Functions.General.{GeneralFormulas, GeneralFunctions}

import scala.scalajs.js.annotation.{JSExportTopLevel, JSExport}

/**
 * The core part of the library that handles the actual simulations.
 */
@JSExportTopLevel("SimCore")
class SimCore(val jobArray: Array[JobInfo]) {

  private val formulaMap = GeneralFormulas.mutMap
  private val generalFunctionMap = GeneralFunctions.hashMap
  private var nextAttacker: (String, Double) = ("None", 0)

  /**
   * Initializes the simulation to prepare it to be ran. Can also be used to reset the sim.
   */
  @JSExport
  def initialize(): Unit ={
    for (job <- jobArray) {
      job.createSimState(new NextAttack)
      FormulaNames.values.foreach(value => job.simState.FormulaResult.put(value, formulaMap(value)(job.simState, 0)._1))
      job.simState.nextAttack.addFunction(generalFunctionMap(GeneralFunctionNames.Opener), GeneralFunctionNames.Opener)
    }
  }
  //TODO: Write run function
  @JSExport
  def runSim(): Unit = {

  }

  /**
   * Executes the next action for the selected job
   * @param simState TODO change to Unique Character Key
   */

  private def nextAction(simState: SimState): Unit= {


    //get the next type of attack from the list and also time until next action
    //TODO change the function from (SimState => Unit) to Array[JobInfo] to accommodate for parties
    val attackTuple: (SimState => Unit, Double) = simState.nextAttack.getNextAttack
    jobArray.foreach(job => {
      job.simState.updateTime(attackTuple._2)
      if (job.simState.nextAttack.peekNextAttack < nextAttacker._2) nextAttacker = (job.characterKey, job.simState.nextAttack.peekNextAttack)
    })

    attackTuple._1(simState)
    //Runs the target attack type and returns the values and states saved in the simModel variable
  }




}

package Core

import BaseClasses.JobInfo
import Enums.{FormulaNames, GeneralFunctionNames}
import timers.NextAttack
import Functions.General.{GeneralFormulas, GeneralFunctions}

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

/**
 * The core part of the library that handles the actual simulations.
 */
@JSExportTopLevel("SimCore")
class SimCore(val jobArray: Array[JobInfo]) {

  private val formulaMap = GeneralFormulas.mutMap
  private val generalFunctionMap = GeneralFunctions.hashMap


  def initialize(): Unit ={
    for (job <- jobArray) {
      job.createSimState(new NextAttack)
      FormulaNames.values.foreach(value => job.simState.FormulaResult.put(value, formulaMap(value)(job.simState, 0)._1))
      job.simState.nextAttack.addFunction(generalFunctionMap(GeneralFunctionNames.Opener), GeneralFunctionNames.Opener)
    }
  }



  //Runs the sim
  @JSExport
  private def nextAction(simState: SimState, nextAttack: NextAttack, damageLog: Array[String]): Unit= {


    //get the next type of attack from the list and also time until next action
    //TODO change the function from (SimState => Unit) to Array[JobInfo] to accommodate for parties
    val attackTuple: (SimState => Unit, Double) = nextAttack.getNextAttack
    jobArray.foreach(job => job.simState.updateTime(attackTuple._2))
    attackTuple._1(simState)
    //Runs the target attack type and returns the values and states saved in the simModel variable



  }


}

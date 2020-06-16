package Core

import BaseClasses.JobInfo
import timers.NextAttack

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

/*
 * The core part of the library that handles the actual simulations.
 */
@JSExportTopLevel("SimCore")
class SimCore(val jobInfo: JobInfo) {


  private val nextAttack: NextAttack = new NextAttack
  private val simModel: SimState = jobInfo.simModel
  private var currentTime: Double = 0

  //simModel.FormulaResult.put("Skill Speed", simModel.formulaMap("Skill Speed")(simModel, 0)._1)
  //simModel.FormulaResult.put("Spell Speed", simModel.formulaMap("Spell Speed")(simModel, 0)._1)
  //simModel.FormulaResult.put("Damage" ,simModel.formulaMap("Damage")(simModel, 0)._1)
  //simModel.FormulaResult.put("Crit", simModel.formulaMap("Crit")(simModel, 0)._1)
  //simModel.FormulaResult.put("Direct Hit", simModel.formulaMap("Direct Hit")(simModel, 0)._1)
  //nextAttack.addFunction(simModel.generalFunctionMap("Opener") , "Test", 1)

  //Runs the sim
  @JSExport
  def nextAction(damageLog: Array[String]): Array[String] = {


    //get the next type of attack from the list and also time until next action
    val attackTuple: (SimState => Unit, Double) = nextAttack.getNextAttack
    currentTime = currentTime + attackTuple._2
    simModel.timeChange = attackTuple._2
    //simModel.generalFunctionMap("TimeChange")(simModel)
    attackTuple._1(simModel)
    //Runs the target attack type and returns the values and states saved in the simModel variable


    damageLog
  }


}

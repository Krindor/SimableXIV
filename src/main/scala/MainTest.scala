import BaseClasses.JobInfo
import Core.SimCore
import Functions.General._
import models._
import timers.NextAttack

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, HashMap => MutMap}

object MainTest {
  def main(args: Array[String]): Unit = {
    val averageFormula: AverageFormula = new AverageFormula
    val generalFormulas: GeneralFormulas = new GeneralFormulas
    val generalAttackFunctions: GeneralAttackFunctions = new GeneralAttackFunctions
    val generalFunctions: GeneralFunctions = new GeneralFunctions
    val attackMap = new mutable.HashMap[String, SkillModel]()
    val buffMap = new mutable.HashMap[String, BuffModel]



    val formulaMap = averageFormula.getFormulas
    formulaMap ++ generalFormulas.getFormulas

    val funcMap = generalFunctions.getFunctions

    val attackFunctionsMap = generalAttackFunctions.getAttackFunctions

    val attackTypeMap = funcMap

    var checkArray = new ArrayBuffer[(SimModel, mutable.Queue[String]) => SimModel](4)
    checkArray += attackFunctionsMap("State Check")
    var queueForCheckArray = new ArrayBuffer[mutable.Queue[String]](4)
    var queueForCheck = new mutable.Queue[String]()
    queueForCheck += "test"
    queueForCheck += "test"
    queueForCheck += "test"
    queueForCheck += "None"

    queueForCheckArray += queueForCheck


    val openerTester: OpenerModel = new OpenerModel("test", "test", 0)
    val skillTester: SkillModel = new SkillModel("test", "test", checkArray, queueForCheckArray)
    attackMap.put("test", skillTester)

    val openerQueue: mutable.Queue[OpenerModel] = new mutable.Queue[OpenerModel]()
    openerQueue += openerTester

    var testMap = new MutMap[String, MutMap[String, BuffModel]]()
    val testMap2 = new MutMap[String, BuffModel]
    testMap2.put("Not Here", new BuffModel)
    testMap.put("test", testMap2)

    var jobInfo: JobInfo = new JobInfo(new StatModel(), "Test", 10)
    jobInfo.createSimModel(openerQueue, new NextAttack, attackTypeMap, formulaMap, jobInfo.statModel, attackMap, attackFunctionsMap, buffMap)
    jobInfo.simModel.soloBuffs.put("test", testMap)
    jobInfo.simModel.soloBuffs.put("State", testMap)

    var simCore: SimCore = new SimCore(jobInfo)

    simCore.nextAction(new Array[String](150))
  }

}

package Functions.General

import Core.SimState

import scala.collection.mutable

object AverageFormula {
  def getFormulas: mutable.HashMap[String, (SimState, Double) => (Double, Double)] = {
    val mutMap: mutable.HashMap[String, (SimState, Double) => (Double, Double)] = new mutable.HashMap[String, (SimState, Double) => (Double, Double)]()
    mutMap.put("CheckCrit", critChance)

    mutMap
  }

  def critChance(simModel: SimState, customMod: Double = 0): (Double, Double) = {
    (simModel.critResult, 2)
  }
}

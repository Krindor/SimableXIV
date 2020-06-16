package models

import Enums.MainStatNames
import Enums.MainStatNames.MainStatNames

class AttributeModel(var strength: Int = 1, var dexterity: Int = 1, var vitality: Int = 1, var intelligence: Int = 1, var mind: Int = 1, val mainStat: MainStatNames = MainStatNames.Strength) {
  val mainStatValue: Int = mainStat match {
    case MainStatNames.Strength => strength
    case MainStatNames.Dexterity => dexterity
    case MainStatNames.Vitality => vitality
    case MainStatNames.Intelligence => intelligence
    case MainStatNames.Mind => mind
    case _ => -1
  }


}

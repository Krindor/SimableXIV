package Factories

import Enums.BuffMapTypes.BuffMapTypes
import Interfaces.SkillModuleInterface
import models.BuffModel
import scala.collection.mutable.{HashMap => MutMap}

//This object is here to make creation of classes with same kind of long instantiation easier
object DuplicateSmacker {

  def createBuffMap(): MutMap[BuffMapTypes, MutMap[String, BuffModel]] = {
    new MutMap[BuffMapTypes, MutMap[String, BuffModel]]()
  }

  def getSkillClass(skillName: String): SkillModuleInterface = {
    Class.forName("SkillModules.General." + skillName).getDeclaredConstructor().newInstance().asInstanceOf[SkillModuleInterface]
  }
}

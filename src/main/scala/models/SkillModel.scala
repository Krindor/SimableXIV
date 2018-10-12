package models

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class SkillModel(val skillName: String, val skillType: String, val  checkArray: ArrayBuffer[(SimModel, mutable.Queue[String]) => Unit],
                 val queueForCheckArray: ArrayBuffer[mutable.Queue[String]], val successPotency: Int = 1, val failPotency: Int = 0, val baseRecast: Double = 2.5, val delayOffset: Double = 0) {
  //Creates a List(touple2) to be iterated later
  private val pairing = checkArray zip queueForCheckArray

  /*
  This one is pretty hard to explain. While it seems simple, there's a lot happening in the functions called.
  The (SimModel, mutable.Queue[String]) => SimModel function is a general skill function that contains one check for
  a skill, for example: to check if the monk's stances matches, it will then apply another function to apply the result.
  The queue contains strings related to the functions called, continuing on the monk example, it would need to know
  what buff to check for and then what the resulting function will be and in that resulting function if there exists
  another function/buff or other effect that needs to be selected. It then updates and returns the SimModel
   */
  def runAttack(oldSimModel: SimModel): Unit ={


    for ((check, queue) <- pairing){

      check(oldSimModel, queue)
      print(" ran check")
    }


  }
}

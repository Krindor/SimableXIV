package models

class BuffModel(var time: Double = 0) {
  def timeChange(timeChange: Double): Unit ={
    time -= timeChange
  }
}

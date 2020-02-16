package com.river.spark.demo2

private[demo2] class PrivateDemo private (val age: Int) {
  val name: String = "jingjing"
  private val faceValue: Int = 0
  private[this] var gender: String = "女"

  def getGender(): String = {
    gender
  }

}

object PrivateDemo {
  def main(args: Array[String]): Unit = {
    val privateDemo = new PrivateDemo(25)
    println("age: " + privateDemo.age)
    println("faceValue: " + privateDemo.faceValue)
    println("gender: " + privateDemo.getGender())
  }
}

object PrivateDemo2 {
  def main(args: Array[String]): Unit = {
//    val privateDemo = new PrivateDemo(26)
//    println("age: " + privateDemo.age)
  }
}

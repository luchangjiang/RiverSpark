package com.river.spark.demo2

class StructDemo(val name: String, var age: Int, faceValue: Int = 98) {

  var gender: String = _
  def getFaceValue(): Int = {
    faceValue
  }

  def this(name: String, age: Int, faceValue: Int, gender: String){
    this(name,age,faceValue)
    this.gender = gender
  }
}

object StructDemo {
  def main(args: Array[String]): Unit = {
    val structDemo = new StructDemo("ning", 34)

    structDemo.age = 35

    println("name: " + structDemo.name + "     age: " + structDemo.age)
    println("faceValue: " + structDemo.getFaceValue())

    val structDemo2 = new StructDemo("ning", 34, 99, "女")
    println(structDemo2.gender)
  }
}

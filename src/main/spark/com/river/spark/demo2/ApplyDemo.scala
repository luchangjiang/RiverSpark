package com.river.spark.demo2

class ApplyDemo (val name: String, var age: Int, faceValue: Int = 98) {

}

object ApplyDemo {
  def apply(name: String, age: Int, faceValue: Int) : ApplyDemo =
    new ApplyDemo(name, age)

  def unapply(arg: ApplyDemo): Option[(String, Int)] = {
    if(arg == null){
      None
    }
    else{
      Some(arg.name, arg.age)
    }
  }
}

object Test {
  def main(args: Array[String]): Unit = {
    val applyDemo = ApplyDemo("jing", 24, 90)

    applyDemo match {
      case ApplyDemo("jing", age) => println(s"age: $age")
      case _ => println("Not matched")
    }
  }
}
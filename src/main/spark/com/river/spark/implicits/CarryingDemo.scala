package com.river.spark.implicits

object CarryingDemo {
  def m1(str: String)(implicit name: String = "scala"): String={
    str +","+ name
  }

  def main(args: Array[String]): Unit = {
    import Constant.lang
    val func = m1("Hi~")
    println(func)
  }
}

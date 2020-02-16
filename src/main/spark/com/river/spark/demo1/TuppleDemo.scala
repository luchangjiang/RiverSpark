package com.river.spark.demo1

object TuppleDemo {
  def main(args: Array[String]): Unit = {
    val tup = ("PI", 3.14, true, ("spark", 4))
    println(tup._4._1)

    val (a, b, c, (e, f))= ("PI", 3.14, true, ("spark", 4))
    println(f)

    println("==============================")
    val arr = Array(("ting", 1),("yue",2),("dan", 3))
    val map = arr.toMap
    for( m <- map){
      println(m._1 + " : " + m._2)
    }
  }
}

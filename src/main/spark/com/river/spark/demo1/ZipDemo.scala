package com.river.spark.demo1

object ZipDemo {
  def main(args: Array[String]): Unit = {
    val arr1 = Array("tom", "mike", "jack")
    val arr2 = Array(24,28,22,44)
    val arrZip = arr1 zip arr2
    for(a <- arrZip){
      println(a._1 + " : " + a._2)
    }
  }
}

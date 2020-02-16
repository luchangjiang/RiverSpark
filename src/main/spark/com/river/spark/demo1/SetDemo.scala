package com.river.spark.demo1

import scala.collection.mutable._

object SetDemo {
  def main(args: Array[String]): Unit = {
    val set1 = new HashSet[Int]()
    set1 +=1;
    for(i <- set1){
      println(i)
    }
    println("=======================")
    set1 += (2,3,4)
    set1 += (3,4,5)
    for(i <- set1){
      println(i)
    }

    println("=======================")
    set1 -= (2)
    set1.remove(3)
    for(i <- set1){
      println(i)
    }
  }
}

package com.river.spark.demo1

object LazyDemo {
  def init(): String ={
    println("init")
    "prop"
  }
  def main(args: Array[String]): Unit = {
    lazy val prop = init()
    println("after init")
    println(prop)
  }
}

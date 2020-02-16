package com.river.spark.demo1

object ArrayDemo {
  def main(args: Array[String]): Unit = {
    val arr: Array[Int] = Array(1,2,3,4,5,6)

    for(i <- arr){
      println(i)
    }

    println("===============================")
    for(i <- (0 until arr.size).reverse){
      println(i)
    }

    println("===============================")
    val res = for(i<-arr) yield i*10
    println(res.toBuffer)

    println("===============================")
    println("sum: " + res.sum)
    println("sorted: " + res.sorted.reverse.toBuffer)

    println("===============================")
    println(1+(.2))
  }

}

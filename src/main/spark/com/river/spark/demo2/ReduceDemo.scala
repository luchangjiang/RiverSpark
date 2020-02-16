package com.river.spark.demo2

object ReduceDemo {
  def main(args: Array[String]): Unit = {
    val arr = Array(1,2,3,4,5,6,7,8,9)


    val line1 = arr.reduce(_+_)
    println(line1)

    //多线程并行计算求和
    val line2 = arr.par.sum
    println(line2)

    //折叠
    val line3 = arr.fold(3)(_+_)
    println(line3)
  }
}

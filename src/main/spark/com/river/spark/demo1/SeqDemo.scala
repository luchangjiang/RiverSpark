package com.river.spark.demo1

import scala.collection.mutable.ListBuffer

object SeqDemo {
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3)
    for(i <- list){
      println(i)
    }

    val list1 = 0::list
    println("list1: " + list1.toBuffer)
    val list2 = 0+:list
    println("list2: " + list2.toBuffer)
    val list3 = list.+:(0)
    println("list3: " + list3.toBuffer)
    val list4 = list:+(4)
    println("list4: " + list4.toBuffer)

    val list5 = list ++ List(4,5,3)
    println("list5: " + list5.toBuffer)

    val list6 = ListBuffer(1,2,3)
    println("list6: " + (list6+=4))
    println("list7: " + (list6 ++ List(7,8,9)))
    println("list8:" + list6.remove(2,1))
  }
}

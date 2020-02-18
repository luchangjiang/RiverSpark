package com.river.spark.implicits

import com.river.spark.entity.Girl

object GodnessContext{
  implicit object OrderingGirl extends Ordering[Girl] {
    override def compare(x: Girl, y: Girl): Int = if (x.faceValue>y.faceValue) 1 else -1
  }
}

class Godness[T: Ordering](val v1: T, val v2: T){
  def choose()(implicit ord: Ordering[T]) = if(ord.gt(v1, v2)) v1 else v2

}
object Godness {
  def main(args: Array[String]): Unit = {
    val g1 = new Girl("ning", 23,97)
    val g2 = new Girl("ting", 28,99)

    import GodnessContext.OrderingGirl
    val girl = new Godness[Girl](g1, g2).choose();

    println(girl.toString)
  }

}

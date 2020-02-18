package com.river.spark.implicits

object GodnessContext{
  implicit object OrderingGirl extends Ordering[Girl] {
    override def compare(x: Girl, y: Girl): Int = if (x.faceValue>y.faceValue) 1 else -1
  }
}

class Girl(val name: String, val faceValue: Int){
  override def toString: String = s"name= $name faceValue=$faceValue"
}

class Godness[T: Ordering](val v1: T, val v2: T){
  def choose()(implicit ord: Ordering[T]) = if(ord.gt(v1, v2)) v1 else v2

}
object Godness {
  def main(args: Array[String]): Unit = {
    val g1 = new Girl("ning", 97)
    val g2 = new Girl("ting", 99)

    import GodnessContext.OrderingGirl
    val girl = new Godness[Girl](g1, g2).choose();

    println(girl.toString)
  }

}

package com.river.spark.bound

class ContextBound[T: Ordering] {
  def choose(t1: T, t2: T): T ={
    val ord: Ordering[T] = implicitly(Ordering[T])
    if(ord.gt(t1, t2)) t1 else t2
  }
}

object ContextBound {
  def main(args: Array[String]): Unit = {
    import PredefContext.selectGirl3
    val contextBound = new ContextBound[Girl]
    val g1 = new Girl("ting", 28, 99)
    val g2 = new Girl("ning", 23, 99)
    val res = contextBound.choose(g1, g2)

    println(res.toString)
  }

}

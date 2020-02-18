package com.river.spark.bound


class ViewBound[T <% Ordered[T]] {
  def choose(t1: T, t2: T): T = {
    if(t1 > t2) t1 else t2
  }
}

object ViewBound {
  def main(args: Array[String]): Unit = {

    import PredefContext.selectGirl2
    val viewBound = new ViewBound[Girl]
    val g1 = new Girl("ting", 28, 99)
    val g2 = new Girl("ning", 23, 99)

    val res = viewBound.choose(g1, g2)
    println(res.toString)
  }
}



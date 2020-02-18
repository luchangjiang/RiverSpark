package com.river.spark.bound

class UpperBound[T <: Comparable[T]]{
  def choose(t1: T, t2: T): T = {
    if(t1.compareTo(t2) > 0) t1 else t2
  }
}


object UpperBound {
  def main(args: Array[String]): Unit = {
    val upperBound = new UpperBound[MyGirl]
    val g1 = new MyGirl("ting", 28, 99)
    val g2 = new MyGirl("ning", 23, 98)

    val res = upperBound.choose(g1, g2)
    println(res.toString)
  }
}

class MyGirl(val name: String, val age: Int, val faceValue: Int) extends Comparable[MyGirl] {
  override def compareTo(o: MyGirl): Int = {
    if(this.faceValue == o.faceValue){
      if(this.age < o.age) 1 else -1
    }
    else{
      if(this.faceValue>=o.faceValue) 1 else -1
    }
  }

  override def toString: String = {
    s"name: $name  age: $age  faceValue: $faceValue"
  }
}


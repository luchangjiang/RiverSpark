package com.river.spark.bound

import com.river.spark.entity.Girl

object PredefContext {
  implicit val selectGirl2 = (g: Girl) => new Ordered[Girl] {
    override def compare(that: Girl): Int = {
      if(g.faceValue == that.faceValue){
        that.age - g.age
      }
      else{
        g.faceValue - that.faceValue
      }
    }
  }

  implicit object selectGirl3 extends Ordering[Girl] {
    override def compare(x: Girl, y: Girl): Int = {
      if(x.faceValue == y.faceValue){
        y.age - x.age
      }
      else{
        x.faceValue - y.faceValue
      }
    }
  }
}

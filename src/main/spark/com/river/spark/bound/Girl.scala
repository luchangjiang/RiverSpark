package com.river.spark.bound

class Girl(val name: String, val age: Int, val faceValue: Int){
  override def toString: String = {
    s"name: $name  age: $age  faceValue: $faceValue"
  }
}

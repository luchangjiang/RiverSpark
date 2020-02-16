package com.river.spark.demo1

object MapDemo {
  def main(args: Array[String]): Unit = {
    import scala.collection.mutable._
    val map1 = Map("scala" -> 1, "java" -> 2, "python" -> 3)
    map1("java")=5
    println(map1.getOrElse("java", "none match"))

    map1 += (("C#", 9))
    map1.put("vb", 8)
    map1.remove("scala")
    for(a <- map1){
      println(a._1 + ":" + a._2)
    }

    println("===========================")

    val map2 = Map(("scala", 1),("java", 2),("python", 3))
    for(a <- map2){
      println(a._1 + ":" + a._2)
    }
    for(b <- map2.values) {
      println(b)
    }
  }
}

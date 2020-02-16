package com.river.spark.demo2

object ExercizeDemo {
  def main(args: Array[String]): Unit = {
    //创建一下List
    val list0 = List(1,2,4,3,6,7,4,3,8)

    //对每个元素*10
    val list1 = list0.map(_*10)
    println("list1: " + list1.toBuffer)

    //取出偶数
    val list2 = list0.filter(_ % 2 == 0)
    println("list2: " + list2.toBuffer)

    //排序及降序
    val list3 = list0.sorted.reverse
    println("list3: " + list3)

    //4个一组，生成一个迭代器
    val it = list0.grouped(4).toList
    println("list4: " + it.toBuffer)
    println("list5: " + it)

    //压扁
    println("flatten: " + it.flatten)

    //先空格切分再压平
    val lines = List("Hello java", "Hello scala", "Hello python")
    val list6 = lines.map(
      _.split(" ")
    ).flatten.toList

    println("list6: " + list6)

    //flatMap
    val list7 = lines.flatMap(_.split(" "))
    println("list7: " + list7)
  }
}

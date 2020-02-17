package com.river.spark.implicits

object RichFileDemo {
  def main(args: Array[String]): Unit = {
    import MyPredef.fileToRichFile
    val file = getClass().getResource("/person.txt").getPath
    print(file.read())
  }
}

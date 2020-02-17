package com.river.spark.implicits

object RichFileDemo {
  def main(args: Array[String]): Unit = {
    import RichFile.fileToRichFile
    val file = getClass().getResource("/person.txt").getPath
    print(file.read())
  }
}

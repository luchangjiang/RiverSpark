package com.river.spark.implicits

import scala.io.Source

class RichFile(val file: String){
  def read(): String = {
    Source.fromFile(file).mkString("")
  }
}

object MyPredef {
  implicit def fileToRichFile(file: String) = new RichFile(file)
}

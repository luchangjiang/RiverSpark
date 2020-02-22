package com.river.spark.topurl

import java.net.URL

import org.apache.spark.{SparkConf, SparkContext}

object TopUrl {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("TopUrl").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val lines = sc.textFile(getClass.getResource("/topurl/browser.txt").getPath)

    val rddA = lines.map(t=>{
      val url = t.split(",")
      val host: String = new URL(url(1)).getHost
      val arr = host.split("\\.")
      (arr(0).trim,1)
    })

    val rddB = rddA.reduceByKey(_+_)

    println(rddB.collect().toList)
    sc.stop()
  }
}

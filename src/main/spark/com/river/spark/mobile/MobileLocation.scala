package com.river.spark.mobile

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object MobileLocation {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("MobileLocation").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val lineA: RDD[String] = sc.textFile(getClass().getResource("/mobile/mobile_a.txt").getPath)
    val lineB: RDD[String] = sc.textFile(getClass().getResource("/mobile/mobile_b.txt").getPath)
    val lineC: RDD[String] = sc.textFile(getClass().getResource("/mobile/mobile_c.txt").getPath)

    val lines = lineA.union(lineB).union(lineC)

    val rddA = lines.map(line => {
      val row = line.split(",")
      val moblie = row(0)
      val flag = row(3).toInt
      val lt = if(flag ==0) row(1).toLong else -row(1).toLong
      val lacId = row(2)
      ((moblie,lacId),lt)
    })

    val rddB = rddA.reduceByKey(_+_)
      .map(x=>{
        //lacId,(mobile,lt)
        (x._1._2,(x._1._1,x._2))
      })

    val lacs: RDD[String] = sc.textFile(getClass.getResource("/mobile/lac_info.txt").getPath)
    val rddLac = lacs.map(la => {
      val row = la.split(",")
      val lacId = row(0)
      val x = row(1).toDouble
      val y = row(2).toDouble

      (lacId,(x,y))
    })

    val rddC: RDD[(String, ((String, Long), (Double, Double)))] = rddB.join(rddLac)

    println(rddC.collect().toBuffer)
    sc.stop()
  }
}

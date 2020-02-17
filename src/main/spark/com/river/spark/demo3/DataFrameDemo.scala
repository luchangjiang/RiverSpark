package com.river.spark.demo3

import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

case class Person (val id: Int, val name: String, val age: Int, val faceValue: Int)

object DataFrameDemo{
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName(getClass().getName).setMaster("local[1]")
    val sc = new SparkContext(conf)

    val sqlContext = new SQLContext(sc)

    val personRDD = sc.textFile(args(0)).map(x=>{
      val arr = x.split(",")
      Person(arr(0).toInt, arr(1), arr(2).toInt, arr(3).toInt)
    })

    import sqlContext.implicits._
    val personDF = personRDD.toDF();

    personDF.registerTempTable("t_person")

    val df: DataFrame = sqlContext.sql("select * from t_person")

    df.write.json(args(1))
    sc.stop()
  }
}

package com.river.spark.demo3

import org.apache.spark.sql.types._
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, Row, SQLContext}

object DataFrameSchema {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName(getClass().getName).setMaster("local[1]")
    val sc = new SparkContext(conf)

    val sqlContext = new SQLContext(sc)

    val personRDD = sc.textFile(args(0)).map(x=>{
      val arr = x.split(",")
      Row(arr(0).toInt, arr(1), arr(2).toInt, arr(3).toInt)
    })

    val schema = StructType(List(
        StructField("id", IntegerType, true),
        StructField("name", StringType, true),
        StructField("age", IntegerType, true),
        StructField("faceValue", IntegerType, true)
    ))

    val personDF = sqlContext.createDataFrame(personRDD, schema)

    personDF.registerTempTable("t_person")

    val df: DataFrame = sqlContext.sql("select * from t_person")

    df.write.json(args(1))
    sc.stop()
  }
}

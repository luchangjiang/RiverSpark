package com.river.spark.actor

import scala.actors.{Actor, Future}
import scala.collection.mutable.ListBuffer
import scala.io.Source

class ActorDemo3 extends Actor {
  override def act(): Unit = {
    while (true){
      receive{
        case SmTask(file) => {
          val res = Source.fromFile(getClass.getResource(file).getPath)
            .getLines().toList.flatMap(_.split(" "))
            .map((_, 1)).groupBy(_._1).mapValues(_.size)

          //异步发送结果
          sender ! res
        }
      }
    }
  }
}

object ActorDemo3 {
  def main(args: Array[String]): Unit = {
    val files = Array("/a.txt","/b.txt","/c.txt")

    val replies: ListBuffer[Future[Any]] = new ListBuffer[Future[Any]]

    for(file <- files){
      val demo = new ActorDemo3
      demo.start()
      val reply = demo !! SmTask(file)
      replies += reply
    }

    while (replies.filter(_.isSet).size < files.size){
      Thread.sleep(100)
    }

    val res: ListBuffer[Map[String,Int]] = new ListBuffer[Map[String, Int]]
    for(reply <- replies){
      res += reply.apply().asInstanceOf[Map[String, Int]]
    }
    println(res.flatten.groupBy(_._1).mapValues(_.foldLeft(0)(_ + _._2)).toList.sorted)
  }

}

case class SmTask(file: String)

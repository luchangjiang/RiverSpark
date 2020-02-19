package com.river.spark.actor

import scala.actors.{Actor, Future}

class ActorDemo2 extends Actor{

  override def act(): Unit ={
    while (true){
      receive{
        case "start" => println("starting...")
        case AsyncMsg(id,msg) => {
          println(s"Async id: $id  msg: $msg")
          Thread.sleep(1000)
          sender ! ReplyMsg(2, "success")
        }
        case SyncMsg(id,msg) => {
          println(s"Sync id: $id  msg: $msg")
          Thread.sleep(2000)
          sender ! ReplyMsg(5, "success")
        }
      }
    }
  }
}

object ActorDemo2 {
  def main(args: Array[String]): Unit = {
    val demo = new ActorDemo2
    demo.start()
    demo ! AsyncMsg(1, "i am Async")
    println("没有返回值的消息！")

    val content = demo !? SyncMsg(2, "i am Sync")
    println("有返回值的同步消息！" + content)

    //异步消息，有返回值
    val reply: Future[Any] = demo !! AsyncMsg(3, "i am Async")
    Thread.sleep(3000)
    if(reply.isSet){
      println(reply.apply())
    }
    else{
      println("None")
    }

  }
}

case class AsyncMsg (id: Int, msg: String)

case class SyncMsg (id: Int, msg: String)

case class ReplyMsg(id: Int, msg: String)

package com.river.spark.akka

import java.util.UUID

import akka.actor.{Actor, ActorSelection, ActorSystem, Props}

import com.typesafe.config.ConfigFactory

class Worker(val host: String, val memory: Int, val port: Int) extends Actor{
  var client: ActorSelection = _
  val idClient: String = UUID.randomUUID().toString

  override def preStart(): Unit= {
    client = context.actorSelection(s"akka.tcp://${MyActor.MASTER_SYSTEM}@${host}:${port}/user/${MyActor.MASTER_ACTOR}")
    client ! RegisterWorker(idClient, memory, port)
  }
  override def receive: Receive = {
    case RegisterResponse(url) => {
      println(url)

      import scala.concurrent.duration._
      import context.dispatcher
      context.system.scheduler.schedule(0 millis,4000 millis,self, HeartBeat)
    }

    case HeartBeat=>{//发生心跳
      println("定时发生心跳给server")

      client ! HeartBeat(idClient)
    }
  }
}

object Worker{
  def main(args: Array[String]): Unit = {
    val host = args(0)
    val masterHost = args(1)
    val port = args(2).toInt
    val masterPort = args(3).toInt
    val memory = args(4).toInt

    val str =
      s"""
         |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname = ${host}
         |akka.remote.netty.tcp.port=${port}
      """.stripMargin

    val conf = ConfigFactory.parseString(str)

    val actorSystem = ActorSystem(MyActor.NODE_SYSTEM, conf)
    actorSystem.actorOf(Props(new Worker(masterHost,memory,masterPort)),MyActor.NODE_ACTOR)
  }
}

package com.river.spark.akka

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.collection.mutable

class Master(val host: String, val port: Int) extends Actor{
  val id2WorkInfo = new mutable.HashMap[String, WorkerInfo]()
  val workers = new mutable.HashSet[WorkerInfo]()

  val TIMEOUT_INTERVAL = 15000

  override def preStart(): Unit={
    import scala.concurrent.duration._
    import context.dispatcher
    context.system.scheduler.schedule(0 millis, 5000 millis, self, CheckTimeOutWorker)
  }

  override def receive: Receive = {
    case RegisterWorker(id: String, memory:Int,cores:Int )=> {//把server的注册信息封装到nmID中
      //当前workerId没有注册
      val workerInfo: WorkerInfo = new WorkerInfo(id,memory,cores)

      id2WorkInfo.put(id,workerInfo)

      workers += workerInfo

      //通知client注册成功
      sender ! RegisterResponse(host+":"+ port)
    }

    case HeartBeat(id) =>{//处理Worker的心跳
      if(null != id2WorkInfo.get(id)){
        val currentTime = System.currentTimeMillis()
        val info = id2WorkInfo(id)
        info.LastHeartbeatTime = currentTime

        id2WorkInfo(id) = info
        workers += info
      }
    }

    case CheckTimeOutWorker=>{//定时检测是否有超时的client并进行处理

      val cur = System.currentTimeMillis
      //过滤超时的client
      val deadWorker = workers.filter(x=>cur-x.LastHeartbeatTime > TIMEOUT_INTERVAL)
      //从记录删除删除超时的worker
      for (w <- deadWorker){
        id2WorkInfo -= w.id
        workers -= w
      }
      println("当前注册成功的节点数" + workers.size)
    }
  }
}

object Master {

  def main(args: Array[String]): Unit = {
    val host = args(0)
    val port = args(1).toInt

    val configStr =
      s"""
         |akka.actor.provider="akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname="$host"
         |akka.remote.netty.tcp.port="$port"
       """.stripMargin

    val config = ConfigFactory.parseString(configStr)
    val actorSystem=ActorSystem(MyActor.MASTER_SYSTEM, config)
    actorSystem.actorOf(Props(new Master(host, port)),MyActor.MASTER_ACTOR)
    actorSystem.awaitTermination()
  }
}

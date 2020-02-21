package com.river.spark.akka

trait RemoteMsg extends Serializable

case class RegisterWorker(val id:String,val memory:Int,val cores: Int)
  extends RemoteMsg

case class HeartBeat(workId: String) extends RemoteMsg

case class RegisterResponse(masterUrl: String) extends RemoteMsg

case object HeartBeat

case object CheckTimeOutWorker


class WorkerInfo(val id:String,val memory: Int,val cores: Int){

  var LastHeartbeatTime: Long = 0
}

object MyActor {
  val MASTER_SYSTEM = "MasterSystem"
  val MASTER_ACTOR = "MasterActor"
  val NODE_SYSTEM = "NodeSystem"
  val NODE_ACTOR = "NodeActor"
}

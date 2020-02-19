package com.river.spark.actor

import scala.actors.Actor

object ActorDemo1 extends Actor{
  override def act(): Unit = {
    for(i <- 1 to 20){
      println("actor1:" + i)
      Thread.sleep(100)
    }
  }
}

object ActorDemo2 extends Actor{
  override def act(): Unit = {
    for(i <- 1 to 20){
      println("actor2:" + i)
      Thread.sleep(100)
    }
  }
}

object ActorTest {
  def main(args: Array[String]): Unit = {
    ActorDemo1.start()
    ActorDemo2.start()
  }
}

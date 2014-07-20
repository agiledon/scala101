package com.agiledon.scala.framework.spray

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import spray.can.Http
import scala.collection.mutable.ListBuffer

object Boot extends App {
  implicit val system = ActorSystem("spray-demo")
  val service = system.actorOf(Props[CustomerServiceActor], "customer-service")
  IO(Http) ! Http.Bind(service, interface = "localhost", port = 8080)

  val l = ListBuffer[Int]()
  l += 1
  l.toList

  val xs = List()
  1 :: xs
  xs.reverse
}

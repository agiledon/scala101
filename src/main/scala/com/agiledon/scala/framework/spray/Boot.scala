package com.agiledon.scala.framework.spray

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import spray.can.Http

object Boot extends App {
  implicit val system = ActorSystem("spray-demo")
  val service = system.actorOf(Props[CustomerServiceActor], "customer-service")
  IO(Http) ! Http.Bind(service, interface = "localhost", port = 8080)
}

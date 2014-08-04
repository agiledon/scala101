package com.agiledon.scala.framework.spray

import spray.httpx.Json4sSupport
import akka.actor._
import scala.concurrent.duration._
import org.json4s.DefaultFormats
import spray.http.StatusCode
import spray.http.StatusCodes._
import akka.actor.SupervisorStrategy.Stop
import spray.routing.RequestContext
import akka.actor.OneForOneStrategy
import com.agiledon.scala.framework.spray.CustomerRequest.WithProps

trait CustomerRequest extends Actor with Json4sSupport {
  def requestContext: RequestContext

  def target: ActorRef

  def message: RequestMessage

  import context._

  //  implicit def json4sFormat = DefaultFormats

  setReceiveTimeout(2.seconds)

  target ! message

  def receive = {
    case Created(location) => complete(spray.http.StatusCodes.Created, location)
    case OneCustomer(customer) => complete(OK, customer)
    case ListCustomers(customers) => complete(OK, customers)
    case Success(message) => complete(OK, message)
    case Error(message) => complete(BadRequest, message)
    case ReceiveTimeout => complete(GatewayTimeout, "Request Timeout")
  }

  def complete[T <: AnyRef](status: StatusCode, obj: T) = {
    requestContext.complete(status, obj)
    stop(self)
  }

  override val supervisorStrategy =
    OneForOneStrategy() {
      case e => {
        complete(InternalServerError, Error(e.getMessage))
        Stop
      }
    }
}

object CustomerRequest {

  case class WithProps(requestContext: RequestContext, props: Props, message: RequestMessage) extends CustomerRequest {
    lazy val target = context.actorOf(props)

    implicit def json4sFormats = DefaultFormats
  }

}

trait CustomerRequestCreator {
  this: Actor =>

  def customerRequest(requestContext: RequestContext, props: Props, message: RequestMessage) =
    context.actorOf(Props(new WithProps(requestContext, props, message)))
}

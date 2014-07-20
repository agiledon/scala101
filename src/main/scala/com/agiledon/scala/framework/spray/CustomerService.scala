package com.agiledon.scala.framework.spray

import akka.actor.{Props, Actor}
import spray.routing.{Route, HttpService}
import spray.httpx.Json4sSupport
import org.json4s.DefaultFormats
import com.agiledon.scala._
import com.agiledon.scala.framework.spray.Customer
import com.agiledon.scala.framework.spray.Customer
import com.agiledon.scala.framework.spray.DeleteCustomer
import com.agiledon.scala.framework.spray.GetCustomer
import com.agiledon.scala.framework.spray.UpdateCustomer
import com.agiledon.scala.framework.spray.CreateCustomer
import com.agiledon.scala.framework.spray.Customer
import com.agiledon.scala.framework.spray.DeleteCustomer
import com.agiledon.scala.framework.spray.GetCustomer
import com.agiledon.scala.framework.spray.UpdateCustomer
import com.agiledon.scala.framework.spray.Customer
import com.agiledon.scala.framework.spray.DeleteCustomer
import com.agiledon.scala.framework.spray.GetCustomer
import com.agiledon.scala.framework.spray.UpdateCustomer
import com.agiledon.scala.framework.spray.CreateCustomer


class CustomerServiceActor extends Actor with CustomerService with CustomerRequestCreator {
  implicit def actorRefFactory = context

  def receive = runRoute(customerRoute)

  def handleRequest(message: RequestMessage): Route =
    ctx => customerRequest(ctx, Props[CustomerActor], message)
}

trait CustomerService extends HttpService with Json4sSupport {
  val json4sFormats = DefaultFormats

  val customerRoute =
    path("customers" / LongNumber) {
      id: Long =>
        get {
          rejectEmptyResponse {
            handleRequest {
              GetCustomer(id)
            }
          }
        } ~ put {
          entity(as[Customer]) {
            customer =>
              handleRequest {
                UpdateCustomer(new Customer(id, customer.birthDate, customer.name))
              }
          }
        } ~ delete {
          handleRequest {
            DeleteCustomer(id)
          }
        }
    } ~ path("customers") {
      get {
        handleRequest {
          AllCustomers
        }
      }
    } ~ post {
      entity(as[Customer]) {
        customer =>
          handleRequest {
            CreateCustomer(customer.birthDate, customer.name)
          }
      }
    }

  def handleRequest(message: RequestMessage): Route
}
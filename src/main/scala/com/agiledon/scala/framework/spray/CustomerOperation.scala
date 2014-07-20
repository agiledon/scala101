package com.agiledon.scala.framework.spray

import java.util.Date
import akka.actor.Actor
import akka.event.Logging
import com.agiledon.scala._
import com.agiledon.scala.framework.spray.Customer
import com.agiledon.scala.framework.spray.Success
import com.agiledon.scala.framework.spray.OneCustomer
import com.agiledon.scala.framework.spray.Customer
import com.agiledon.scala.framework.spray.DeleteCustomer
import com.agiledon.scala.framework.spray.ListCustomers
import com.agiledon.scala.framework.spray.GetCustomer
import com.agiledon.scala.framework.spray.UpdateCustomer
import com.agiledon.scala.framework.spray.CreateCustomer

trait CustomerOperations {

  def getById(id: Long) = {
    OneCustomer(new Customer(id, new Date(1000), "item1"))
  }

  def all() =  {
    try{
      ListCustomers(List(new Customer(100, new Date(1000), "item1"),
      new Customer(101, new Date(1200), "zhangyi")))
    } catch{
      case e:Exception => {
        println(e.getMessage())
        List()
      }
    }
  }

  def delete(id: Long) = {
    Success("deleted successfully")
  }

  def create (dueDate: Date, text: String) =  {

    Created("")
  }

  def update (customer: Customer) = {
    getById(customer.id)
  }
}

class CustomerActor extends Actor with CustomerOperations{
  val log = Logging(context.system, this)
  def receive = {
    case GetCustomer(id) => sender ! getById(id)
    case UpdateCustomer(item) => sender ! update(item)
    case DeleteCustomer(id) => sender ! delete(id)
    case CreateCustomer(dueDate, text) => sender ! create(dueDate, text)
    case AllCustomers => sender ! all()
  }
}

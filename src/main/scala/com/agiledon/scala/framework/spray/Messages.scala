package com.agiledon.scala.framework.spray

import java.util.Date

sealed trait ResultMessage

case class Customer(id: Long, birthDate: Date, name: String)

case class Created(location: String) extends ResultMessage
case class Success(message: String) extends ResultMessage
case class OneCustomer(item: Customer) extends ResultMessage
case class ListCustomers(items: List[Customer]) extends ResultMessage

case class Error(message: String)

sealed trait RequestMessage

case class GetCustomer(id: Long) extends RequestMessage
case class DeleteCustomer(id: Long) extends RequestMessage
case class UpdateCustomer(item: Customer) extends RequestMessage
case class CreateCustomer(dueDate: Date, text: String) extends RequestMessage

case object AllCustomers extends RequestMessage




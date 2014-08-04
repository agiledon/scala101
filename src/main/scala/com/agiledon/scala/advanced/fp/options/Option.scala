package com.agiledon.scala.advanced.fp.options

import scala.{Option => _}

sealed trait Option[+A] {
  def map[B](f: A => B): Option[B] =  this match {
    case None => None
    case Some(x) => Some(f(x))
  }
  def getOrElse[B >: A](default: => B): B = this match {
    case None => default
    case Some(x) => x
  }

  def get():A = this match {
    case Some(x) => x
  }

  def flatMap[B](f: A => Option[B]): Option[B] = map(f) getOrElse None

  def orElse[B >: A](ob: => Option[B]): Option[B] = this match {
    case None => ob
    case Some(_) => this
  }
  //注意orElse1的实现比较古怪，但很有意义
  def orElse1[B >: A](ob: => Option[B]): Option[B] = {
    this map(Some(_)) getOrElse ob
  }

  def filter(f: A => Boolean): Option[A] = this match {
    case Some(x) if (f(x)) => this
    case None => None
  }
}

case class Some[+A](element: A) extends Option[A]
case object None extends Option[Nothing]

object Option {
  def sequence[A](a: List[Option[A]]): Option[List[A]] = {
    a match {
      case Nil => Some(Nil)
      case _ => if (a.contains(None)) None
      else Some(a.map(_.get))
    }
  }

  def sequence1[A](a: List[Option[A]]): Option[List[A]] = {
    a match {
      case Nil => Some(Nil)
      case head :: tail => head flatMap (h => sequence1(tail) map (e => h :: e))
    }
  }
}

package com.agiledon.scala.basic.patterns

import scala.io.Source

case class Path(value: String) extends AnyVal

//notes: based on this implementation -> map function
//extract write and read to specific trait, eg: ReadSupport, WriteSupport
class FileTransformer extends FileLike {

  def toUpperCase(path: Path): Unit = {
    write(path, getContent(path).map(_.toUpperCase()))
  }

  def toLowerCase(path: Path): Unit = {
    write(path, getContent(path).map(_.toLowerCase()))
  }

  //replace space with separator
  def separate(path: Path, separator: String): Unit = {
    write(path, getContent(path).map(_.replace(" ", separator)))
  }

  def getContent(path: Path): List[String] = {
    Source.fromFile(path.value).getLines().toList
  }

  //next step -> Generic
  //note: return value
  def map(path: Path, f: String => String): Unit = ???

  //refactor map to Persist
  trait Persist[T] {
    def map[U](path: Path, f: T => U): Persist[U] = ???
  }

}

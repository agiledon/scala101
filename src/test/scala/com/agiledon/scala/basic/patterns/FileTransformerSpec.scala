package com.agiledon.scala.basic.patterns

import com.agiledon.scala.UnitSpec
import scala.io._
import org.scalatest.BeforeAndAfter

class FileTransformerSpec extends UnitSpec with BeforeAndAfter with FileLike {
  val transformer = new FileTransformer()
  val resourcePath = "/Users/twer/workspace/scala101/data/sample.data"
  val content = List("This is the test data.", "Second Line")

  before {
    delete(Path(resourcePath))
    write(Path(resourcePath), content)
  }

  it should "convert file content to upper case" in {
    makeSureContentIsCorrect

    transformer.toUpperCase(Path(resourcePath))

    val transformed = Source.fromFile(resourcePath).getLines().toList
    transformed.head should be(content.head.toUpperCase())
  }

  it should "convert file content to lower case" in {
    makeSureContentIsCorrect

    transformer.toLowerCase(Path(resourcePath))

    val transformed = Source.fromFile(resourcePath).getLines().toList
    transformed.head should be(content.head.toLowerCase())
  }

  it should "add separator for file content" in {
    makeSureContentIsCorrect

    transformer.separate(Path(resourcePath), ",")

    val transformed = Source.fromFile(resourcePath).getLines().toList
    transformed.head should be("This,is,the,test,data.")
  }


  def makeSureContentIsCorrect {
    val lines = Source.fromFile(resourcePath).getLines().toList
    lines.mkString should be(content.mkString)
  }
}

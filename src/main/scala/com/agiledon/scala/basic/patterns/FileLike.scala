package com.agiledon.scala.basic.patterns

import java.io.{FileWriter, BufferedWriter, File}

trait FileLike {
  def write(path: Path, content: List[String]): Unit = {
    var writer:BufferedWriter = null
    try {
      val file = new File(path.value)
      writer = new BufferedWriter(new FileWriter(file))
      content.foreach {
        line =>
          writer.write(line)
          writer.newLine()
      }
      writer.flush()
    } finally {
      if (writer != null) writer.close()
    }
  }

  def delete(path: Path): Unit = {
    val file = new File(path.value)
    if (file.exists()) {
      file.delete()
    }
  }

}

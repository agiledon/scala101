package com.agiledon.scala.framework.spark.streaming

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.StreamingContext._

object HdfsWordCount extends App {
  val ssc = new StreamingContext("local[2]", "HdfsWordCount", Seconds(2))
  val lines = ssc.textFileStream("samples")
  val wordCount = lines.flatMap(_.split(" ")).map(x => (x, 1)).reduceByKey(_ + _)

  wordCount.print()

  ssc.start()
  ssc.awaitTermination()
}

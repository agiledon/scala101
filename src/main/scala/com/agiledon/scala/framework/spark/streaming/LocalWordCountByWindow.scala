package com.agiledon.scala.framework.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.StreamingContext._

object LocalWordCountByWindow extends App {
  val sparkConf = new SparkConf().setAppName("HdfsWordCount")
  val ssc = new StreamingContext(sparkConf, Seconds(2))

  val lines = ssc.textFileStream("/Users/twer/workspace/scala101/data")   //local directory
  val wordDstream = lines.flatMap(_.split(" ")).map(x => (x, 1))
  val wordCount = wordDstream.reduceByKeyAndWindow((a: Int, b: Int) => a + b, Seconds(10), Seconds(8))
  wordCount.print()

  ssc.start()
  ssc.awaitTermination()
}

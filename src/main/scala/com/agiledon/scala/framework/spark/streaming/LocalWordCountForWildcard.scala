package com.agiledon.scala.framework.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.StreamingContext._

object LocalWordCountForWildcard extends App {
  val sparkConf = new SparkConf().setAppName("LocalWordCountForWildcard")
  val ssc = new StreamingContext(sparkConf, Seconds(2))

  //todo: Not work;
  // Spark String is not support to monitor path given wildcard
  val lines = ssc.textFileStream("/Users/twer/workspace/scala101/data/*01.txt")
  val wordCount = lines.flatMap(_.split(" ")).map(x => (x, 1)).reduceByKey(_ + _)

  wordCount.print()

  ssc.start()
  ssc.awaitTermination()
}

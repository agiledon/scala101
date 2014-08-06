package com.agiledon.scala.framework.spark.streaming

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.StreamingContext._

object NetworkWordCount extends App {
    val ssc = new StreamingContext("local[4]", "NetworkWordCount", Seconds(2))
    val lines = ssc.socketTextStream("localhost", 9999)
    val wordCount = lines.flatMap(_.split(" ")).map(x => (x, 1)).reduceByKey(_ + _)

    wordCount.print()

    ssc.start()
    ssc.awaitTermination()
}

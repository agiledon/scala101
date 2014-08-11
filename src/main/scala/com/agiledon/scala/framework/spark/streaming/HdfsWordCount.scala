package com.agiledon.scala.framework.spark.streaming

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.SparkConf

//how to run this app
//use spark-submit like this:
//./spark-submit --class com.agiledon.scala.framework.spark.streaming.HdfsWordCount --master local /Users/twer/workspace/scala101/target/scala-2.10/scala101_2.10-0.1.jar 100
object HdfsWordCount extends App {
  val sparkConf = new SparkConf().setAppName("HdfsWordCount")
//  val ssc = new StreamingContext("local[2]", "HdfsWordCount", Seconds(5))
  val ssc = new StreamingContext(sparkConf, Seconds(2))

  val updateFunc = (values: Seq[Int], state: Option[Int]) => {
    val currentCount = values.foldLeft(0)(_ + _)
    val previousCount = state.getOrElse(0)
    Some(currentCount + previousCount)
  }
  
  val lines = ssc.textFileStream("/Users/twer/workspace/scala101/data")   //local directory
  val wordCount = lines.flatMap(_.split(" ")).map(x => (x, 1)).reduceByKey(_ + _)

  wordCount.print()

  ssc.start()
  ssc.awaitTermination()
}

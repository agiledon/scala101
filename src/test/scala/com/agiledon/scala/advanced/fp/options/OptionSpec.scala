package com.agiledon.scala.advanced.fp.options

import com.agiledon.scala.UnitSpec

class OptionSpec extends UnitSpec {
  "get" should "get real value" in {
    val s = Some("abc")
    s.get should be("abc")
  }

  "sequence" should "sequence all Some instances" in {
    val s = List(Some("123"), Some("abc"), Some("ABD"))
    Option.sequence(s) should be(Some(List("123", "abc", "ABD")))
  }

  "sequence" should "return None if List contains None" in {
    val s = List(Some("123"), None, Some("ABD"))
    Option.sequence(s) should be(None)
  }

  "sequence" should "return None given Nil" in {
    val s = List()
    Option.sequence(s) should be(Some(Nil))
  }

  "sequence1" should "sequence all Some instances using" in {
    val s = List(Some("123"), Some("abc"), Some("ABD"))
    Option.sequence1(s) should be(Some(List("123", "abc", "ABD")))
  }

  "sequence1" should "return None if List contains None" in {
    val s = List(Some("123"), None, Some("ABD"))
    Option.sequence1(s) should be(None)
  }

  "sequence1" should "return None given Nil" in {
    val s = List()
    Option.sequence1(s) should be(Some(Nil))

    scala.Option
  }

}

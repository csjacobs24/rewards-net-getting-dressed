package com.rewardsnet.getdressed.app

import com.rewardsnet.getdressed.dress.Temperature.HOT
import com.rewardsnet.getdressed.dress.Command
import org.scalatest.{FlatSpec, Matchers}

class consumeArgsSpec extends FlatSpec with Matchers {
  "Arguments" should "be read in correctly" in {
    consumeArgs(Array("HOT", "1,", "2,", "3,", "4")) shouldBe Command(HOT, Vector(1, 2, 3, 4))
  }
}

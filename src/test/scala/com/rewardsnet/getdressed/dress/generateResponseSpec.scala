package com.rewardsnet.getdressed.dress

import org.scalatest.{FlatSpec, Matchers}

class generateResponseSpec extends FlatSpec with Matchers {

  "Successful response" should "be correct for hot temperature" in {
    val expectedResponse = "Removing PJs, shorts, shirt, sunglasses, sandals, leaving house"
    val actualResponse = generateResponse(Command(Temperature.HOT, Vector(8, 6, 4, 2, 1, 7)))
    actualResponse shouldBe expectedResponse
  }

  "Successful response" should "be correct for cold temperature" in {
    val expectedResponse = "Removing PJs, pants, socks, shirt, hat, jacket, boots, leaving house"
    val actualResponse = generateResponse(Command(Temperature.COLD, Vector(8, 6, 3, 4, 2, 5, 1, 7)))
    actualResponse shouldBe expectedResponse
  }

  "Failing response" should "be correct for repeated clothing items" in {
    val expectedResponse = "Removing PJs, shorts, fail"
    val actualResponse = generateResponse(Command(Temperature.HOT, Vector(8, 6, 6)))
    actualResponse shouldBe expectedResponse
  }

  "Failing response" should "be correct for illegal socks" in {
    val expectedResponse = "Removing PJs, shorts, fail"
    val actualResponse = generateResponse(Command(Temperature.HOT, Vector(8, 6, 3)))
    actualResponse shouldBe expectedResponse
  }

  "Failing response" should "be correct for missing shoes" in {
    val expectedResponse = "Removing PJs, pants, socks, shirt, hat, jacket, fail"
    val actualResponse = generateResponse(Command(Temperature.COLD, Vector(8, 6, 3, 4, 2, 5, 7)))
    actualResponse shouldBe expectedResponse
  }

  "Missing shoes" should "not be a problem if you don't leave the house" in {
    val expectedResponse = "Removing PJs, pants, socks, shirt, hat, jacket"
    val actualResponse = generateResponse(Command(Temperature.COLD, Vector(8, 6, 3, 4, 2, 5)))
    actualResponse shouldBe expectedResponse
  }

  "Failing response" should "be correct for no pajama removal" in {
    val expectedResponse = "fail"
    val actualResponse = generateResponse(Command(Temperature.COLD, Vector(6)))
    actualResponse shouldBe expectedResponse
  }
}

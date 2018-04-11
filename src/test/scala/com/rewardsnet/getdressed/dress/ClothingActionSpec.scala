package com.rewardsnet.getdressed.dress

import Temperature._
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.{FlatSpec, Matchers}

import scala.util.{Failure, Success, Try}

class ClothingActionSpec extends FlatSpec with Matchers {
  "Input ints" should "map correctly to clothing actions" in {
    ClothingAction(1) shouldBe PutOnFootwear
    ClothingAction(2) shouldBe PutOnHeadwear
    ClothingAction(3) shouldBe PutOnSocks
    ClothingAction(4) shouldBe PutOnShirt
    ClothingAction(5) shouldBe PutOnJacket
    ClothingAction(6) shouldBe PutOnPants
    ClothingAction(7) shouldBe LeaveHouse
    ClothingAction(8) shouldBe RemovePajamas
  }

  "Success responses" should "be as expected" in {
    val responses = Table(
      ("action", "HotResponse", "ColdResponse"),
      (PutOnFootwear, Success("sandals"), Success("boots")),
      (PutOnHeadwear, Success("sunglasses"), Success("hat")),
      (PutOnSocks, failAtDressing(), Success("socks")),
      (PutOnShirt, Success("shirt"), Success("shirt")),
      (PutOnJacket, failAtDressing(), Success("jacket")),
      (PutOnPants, Success("shorts"), Success("pants")),
      (LeaveHouse, Success("leaving house"), Success("leaving house")),
      (RemovePajamas, Success("Removing PJs"), Success("Removing PJs"))
    )

    forAll (responses) { (action, hotResponse, coldResponse) =>
      checkResponse(action, HOT, hotResponse)
      checkResponse(action, COLD, coldResponse)
    }

    def checkResponse(action: ClothingAction, temperature: Temperature.Value, response: Try[String]) = {
      action.response(temperature) match {
        case r: Success[String] =>
          r.get shouldBe response.get
        case r: Failure[String] =>
          a [RuntimeException] should be thrownBy r.get
          val thrown = the [RuntimeException] thrownBy r.get
          thrown.getMessage shouldBe "fail"
      }
    }
  }

  "Illegal input ints" should "result in an illegal argument exception" in {
    a [RuntimeException] should be thrownBy ClothingAction(3000)
    val thrown = the [RuntimeException] thrownBy ClothingAction(3000)
    thrown.getMessage shouldBe "fail"
  }
}

package com.rewardsnet.getdressed.dress

import org.scalatest.{FlatSpec, Matchers}
import Temperature._

class RulesSpec extends FlatSpec with Matchers {
  "Pajamas" should "be taken off before anything else can be put on" in {
    Rules(PutOnPants, Vector()).pajamasOffFirst shouldBe false
    Rules(PutOnPants, Vector(RemovePajamas)).pajamasOffFirst shouldBe true
  }

  "Only 1 piece of each type of clothing" should "be put on" in {
    Rules(PutOnPants, Vector(PutOnPants, PutOnJacket)).onlyOneOfEach shouldBe false
    Rules(PutOnPants, Vector(PutOnJacket)).onlyOneOfEach shouldBe true
  }

  "You" should "not put on socks when it is hot" in {
    val withSocks = Rules(PutOnSocks, Vector(RemovePajamas, PutOnPants))
    withSocks.clothingIsAllowed(HOT) shouldBe false
    withSocks.clothingIsAllowed(COLD) shouldBe true
  }

  "You" should "not put on a jacket when it is hot" in {
    val withJacket = Rules(PutOnJacket, Vector(RemovePajamas, PutOnPants, PutOnShirt, PutOnHeadwear))
    withJacket.clothingIsAllowed(HOT) shouldBe false
    withJacket.clothingIsAllowed(COLD) shouldBe true

  }

  "You" should "not put on socks and a jacket when it is hot" in {
    val withSocksAndJacket = Rules(PutOnJacket, Vector(RemovePajamas, PutOnPants, PutOnSocks, PutOnShirt, PutOnHeadwear))
    withSocksAndJacket.clothingIsAllowed(HOT) shouldBe false
    withSocksAndJacket.clothingIsAllowed(COLD) shouldBe true
  }

  "Socks" should "be put on before footwear" in {
    Rules(PutOnSocks, Vector(PutOnFootwear)).socksBeforeShoes shouldBe false
    Rules(PutOnFootwear, Vector(PutOnSocks)).socksBeforeShoes shouldBe true
  }

  "Pants" should "be put on before footwear" in {
    Rules(PutOnPants, Vector(PutOnFootwear)).pantsBeforeShoes shouldBe false
    Rules(PutOnFootwear, Vector(PutOnSocks)).pantsBeforeShoes shouldBe true
  }

  "The shirt" should "be put on before the headwear" in {
    Rules(PutOnShirt, Vector(PutOnHeadwear)).shirtBeforeHeadwear shouldBe false
    Rules(PutOnHeadwear, Vector(PutOnShirt)).shirtBeforeHeadwear shouldBe true
  }

  "The shirt" should "be put on before the jacket" in {
    Rules(PutOnShirt, Vector(PutOnJacket)).shirtBeforeJacket shouldBe false
    Rules(PutOnJacket, Vector(PutOnShirt)).shirtBeforeJacket shouldBe true
  }

  "You" should "not leave the house until all items of clothing are on" in {
    val correctHot = Vector(RemovePajamas, PutOnPants, PutOnShirt, PutOnHeadwear, PutOnFootwear)
    val correctCold = Vector(RemovePajamas, PutOnPants, PutOnSocks, PutOnShirt, PutOnHeadwear, PutOnJacket, PutOnFootwear)
    Rules(LeaveHouse, correctHot).dressBeforeLeaving(HOT) shouldBe true
    Rules(LeaveHouse, correctCold).dressBeforeLeaving(COLD) shouldBe true

    correctHot.combinations(correctHot.size - 1).foreach { missingAnItem =>
      Rules(LeaveHouse, missingAnItem).dressBeforeLeaving(HOT) shouldBe false
    }

    correctCold.combinations(correctHot.size - 1).foreach { missingAnItem =>
      Rules(LeaveHouse, missingAnItem).dressBeforeLeaving(COLD) shouldBe false
    }
  }
}

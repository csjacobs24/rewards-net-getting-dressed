package com.rewardsnet.getdressed.dress

case class Command(temp: Temperature.Value, actionNumbers: Vector[Int])

object Command {
  val ShoesOn = 1
  val HeadwearOn = 2
  val SocksOn = 3
  val ShirtOn = 4
  val JacketOn = 5
  val PantsOn = 6
  val Leave = 7
  val PjsOff = 8

  val CommandToAction = Map(
    ShoesOn -> PutOnFootwear,
    HeadwearOn -> PutOnHeadwear,
    SocksOn -> PutOnSocks,
    ShirtOn -> PutOnShirt,
    JacketOn -> PutOnJacket,
    PantsOn -> PutOnPants,
    Leave -> LeaveHouse,
    PjsOff -> RemovePajamas
  )
}

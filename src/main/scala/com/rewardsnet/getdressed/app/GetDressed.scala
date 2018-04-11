package com.rewardsnet.getdressed.app

import com.rewardsnet.getdressed.dress
import com.rewardsnet.getdressed.dress.generateResponse

object GetDressed extends App {
  val command = consumeArgs(args)
  println(dress.generateResponse(command))
}

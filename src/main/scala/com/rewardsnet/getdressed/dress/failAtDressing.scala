package com.rewardsnet.getdressed.dress

import scala.util.Failure

object failAtDressing {
  def apply() = Failure(new RuntimeException("fail"))
}

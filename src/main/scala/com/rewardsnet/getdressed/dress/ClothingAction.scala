package com.rewardsnet.getdressed.dress

import com.rewardsnet.getdressed.dress.ClothingAction.Response
import com.rewardsnet.getdressed.dress.Temperature._

import scala.util.{Success, Try}

//Todo: Implement the rest of these

sealed trait ClothingAction {
  val response: Response
}

object PutOnFootwear extends ClothingAction {
  override val response: Response = {
    case HOT => Success("sandals")
    case COLD => Success("boots")
  }
}

object PutOnHeadwear extends ClothingAction {
  override val response: Response = {
    case HOT => Success("sunglasses")
    case COLD => Success("hat")
  }
}

object PutOnSocks extends ClothingAction {
  override val response: Response = {
    case HOT => failAtDressing()
    case COLD => Success("socks")
  }
}

object PutOnShirt extends ClothingAction {
  override val response: Response = {
    case HOT => Success("shirt")
    case COLD => Success("shirt")
  }
}

object PutOnJacket extends ClothingAction {
  override val response: Response = {
    case HOT => failAtDressing()
    case COLD => Success("jacket")
  }
}

object PutOnPants extends ClothingAction {
  override val response: Response = {
    case HOT => Success("shorts")
    case COLD => Success("pants")
  }
}

object LeaveHouse extends ClothingAction {
  override val response: Response = {
    case HOT => Success("leaving house")
    case COLD => Success("leaving house")
  }
}

object RemovePajamas extends ClothingAction {
  override val response: Response = {
    case HOT => Success("Removing PJs")
    case COLD => Success("Removing PJs")
  }
}

object ClothingAction {
  type Response = Temperature.Value => Try[String]

  def apply(command: Int): ClothingAction = {
    Command.CommandToAction.getOrElse(command, failAtDressing().get)
  }
}
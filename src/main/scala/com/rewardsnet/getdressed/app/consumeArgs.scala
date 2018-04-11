package com.rewardsnet.getdressed.app

import com.rewardsnet.getdressed.dress.{Command, Temperature, failAtDressing}

import scala.util.Try

object consumeArgs {
  def apply(args: Array[String]): Command = {
    val temp = Try(Temperature.withName(args.head)).getOrElse(failAtDressing().get)

    val actions = args.tail.toVector.map { arg =>
      Try(arg.replace(",", "").toInt).getOrElse(failAtDressing().get)
    }

    Command(temp, actions)
  }
}
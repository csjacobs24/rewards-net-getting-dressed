package com.rewardsnet.getdressed.dress

object generateResponse {
  def apply(command: Command): String = {
    val temp = command.temp

    val actions = command.actionNumbers.map(actionNumber => ClothingAction(actionNumber))

    val validActions = actions.zipWithIndex.takeWhile { case (currentAction, index) =>
      val previousActions = actions.take(index)
      Rules(currentAction, previousActions).allRulesFollowed(temp)
    }.map(_._1)

    val response = s"${validActions.map(_.response(temp).get).mkString(", ")}"

    if (validActions.size == actions.size) response
    else if (validActions.isEmpty) "fail"
    else s"$response, fail"
  }
}

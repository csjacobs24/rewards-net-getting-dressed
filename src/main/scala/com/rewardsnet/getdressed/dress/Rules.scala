package com.rewardsnet.getdressed.dress

import com.rewardsnet.getdressed.dress.Rules._
import com.rewardsnet.getdressed.dress.Temperature._

class Rules(currentAction: ClothingAction, previousActions: Vector[ClothingAction]) {
  private val actions: Vector[ClothingAction] = previousActions :+ currentAction
  val pajamasOffFirst: Boolean = actions.head == RemovePajamas
  val socksBeforeShoes: Boolean = PutOnSocks.before(PutOnFootwear)
  val pantsBeforeShoes: Boolean = PutOnPants.before(PutOnFootwear)
  val shirtBeforeHeadwear: Boolean = PutOnShirt.before(PutOnHeadwear)
  val shirtBeforeJacket: Boolean = PutOnShirt.before(PutOnJacket)
  val onlyOneOfEach: Boolean = !previousActions.contains(currentAction)
  def clothingIsAllowed(temp: Temperature.Value): Boolean = currentAction.response(temp).isSuccess

  private def allDressed(temp: Temperature.Value) = temp match {
    case HOT => previousActions.toSet == hotWeatherActions.toSet
    case COLD => previousActions.toSet == coldWeatherActions.toSet
  }

  def dressBeforeLeaving(temp: Temperature.Value): Boolean = currentAction != LeaveHouse || allDressed(temp)

  def allRulesFollowed(temp: Temperature.Value): Boolean = {
    Vector(
      pajamasOffFirst,
      socksBeforeShoes,
      pantsBeforeShoes,
      shirtBeforeHeadwear,
      shirtBeforeJacket,
      onlyOneOfEach,
      clothingIsAllowed(temp),
      dressBeforeLeaving(temp)
    ).forall(_ == true)
  }

  implicit class ClothingActionExt(firstAction: ClothingAction) {
    def before(secondAction: ClothingAction): Boolean = {
      // If you're trying to do the first action, you can't already have done the second
      !(currentAction == firstAction && previousActions.contains(secondAction))
    }
  }
}

object Rules {
  def apply(currentAction: ClothingAction, previousActions: Vector[ClothingAction]): Rules =
    new Rules(currentAction, previousActions)

  val hotWeatherActions = Vector(PutOnFootwear, PutOnShirt, PutOnHeadwear, PutOnPants, RemovePajamas)
  val coldWeatherActions = hotWeatherActions ++ Vector(PutOnJacket, PutOnSocks)
}
package state
import scalaz.State
import scalaz.Scalaz._

object StateExercise extends App{
  trait Colour
  object Red extends Colour { override def toString = "Red"}
  object Green extends Colour { override def toString = "Green"}
  object Blue extends Colour { override def toString = "Blue"}

  type Bag = Map[Colour, Int]
  val empty: State[Bag, Bag] = state(Map[Colour, Int]())

  def drawOne(soFar: Bag): State[Bag, Bag] = for(
    ball <- State[Bag, Colour]{s =>
      val (colours, counts) = s.toIndexedSeq.unzip
      val ballIndex = (math.random * s.values.sum).toInt
      val selected = colours(
        counts.tail
          .view
          .scanLeft(counts(0))(_ + _)
          .indexWhere(_ >= ballIndex)
      )
      (s.updated(selected, s(selected) - 1), selected)
    }
  ) yield soFar.updated(ball, soFar.getOrElse(ball, 0) + 1)

  def draw(n: Int): State[Bag, Bag] = for(
    balls <- get[Bag];
    selection <- {
      assert(balls.values.sum >= n)   //Check the bag has enough left
      (1 to n).foldLeft(empty)((accState, _) => accState.flatMap(drawOne))
    }
  ) yield selection

  val full: Bag = Map(Red -> 30, Green -> 20, Blue -> 10)
  val (remaining, selected) = draw(5)(full)
  println(" Selected: "+selected)
  println("Remaining: "+remaining)
}

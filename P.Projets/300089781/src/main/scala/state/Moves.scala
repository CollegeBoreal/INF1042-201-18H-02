package state

import scala.util.Random

trait Move
case object Rock extends Move
case object Paper extends Move
case object Scissors extends Move

trait Moves {

  protected val moves = List(Rock,Paper,Scissors)

  protected val beats:Map[Move,List[Move]]

  protected def randomMove:Move = moves(Random.nextInt(moves.size))

  protected def canBeat(m1: Move, m2: Move) = beats.get(m1).exists(_.contains(m2))

}


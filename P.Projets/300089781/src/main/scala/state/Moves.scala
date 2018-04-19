package state

import scala.util.Random

trait Move
case object Roche extends Move
case object Papier extends Move
case object Ciseaux extends Move

trait Moves {

  protected val moves = List(Roche,Papier,Ciseaux)

  protected val beats:Map[Move,List[Move]]

  protected def randomMove:Move = moves(Random.nextInt(moves.size))

  protected def canBeat(m1: Move, m2: Move) = beats.get(m1).exists(_.contains(m2))

}


package state

import scalaz.State

trait GameContext extends Moves {

    def play(p1: Player, p2: Player): GameResult = p1.move == p2.move match {
      case true => Tie
      case _ if canBeat(p1.move, p2.move) => Win(p1)
      case _ => Win(p2)

  }
  def randomPlayer(name: String) = Player(name, randomMove)
}

trait RockPaperScissors extends GameContext {
  override protected val beats: Map[Move, List[Move]] =
    Map(Roche -> List(Ciseaux), Ciseaux -> List(Papier), Papier -> List(Roche))
}

sealed trait GameResult

case object Tie extends GameResult

case class Win(player: Player) extends GameResult

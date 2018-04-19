package state

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

trait RockPaperScissorsSpockLizard extends GameContext {

  case object Spock extends Move

  case object Lizard extends Move

  override val moves = List(Roche, Papier, Ciseaux, Spock, Lizard)

  override protected val beats: Map[Move, List[Move]] =
    Map(Roche -> List(Ciseaux, Lizard), Ciseaux -> List(Papier, Lizard), Papier -> List(Roche, Spock),
      Spock -> List(Ciseaux, Roche), Lizard -> List(Papier, Spock))

}

sealed trait GameResult

case object Tie extends GameResult

case class Win(player: Player) extends GameResult

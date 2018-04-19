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
    Map(Rock -> List(Scissors), Scissors -> List(Paper), Paper -> List(Rock))
}

trait RockPaperScissorsSpockLizard extends GameContext {

  case object Spock extends Move

  case object Lizard extends Move

  override val moves = List(Rock, Paper, Scissors, Spock, Lizard)

  override protected val beats: Map[Move, List[Move]] =
    Map(Rock -> List(Scissors, Lizard), Scissors -> List(Paper, Lizard), Paper -> List(Rock, Spock),
      Spock -> List(Scissors, Rock), Lizard -> List(Paper, Spock))

}

sealed trait GameResult

case object Tie extends GameResult

case class Win(player: Player) extends GameResult

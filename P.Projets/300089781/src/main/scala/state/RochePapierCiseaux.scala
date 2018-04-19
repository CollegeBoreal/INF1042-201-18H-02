package state
import scala.util.Random
import java.io.{Reader, InputStreamReader}
import scala.io.StdIn

object RochePapierCiseaux {

  case class Player(name: String, move: Move) {
    override def toString = name
  }

  class GameApp(in: InputParser) {
    self: GameContext =>

    private def printResult(result: GameResult): Unit = result match {
      case Win(player) => println(s"The winner is '$player'")
      case Tie => println("It was a Tie")
    }

    private def printMatch(p1: Player, p2: Player): Unit = {
      println(s"${p1.name} chose '${p1.move}'")
      println(s"${p2.name} chose '${p2.move}'")
      printResult(play(p1, p2))
    }

    def start(): Unit = {
      println("Starting a new Game")
      in.chooseMode() match {
        case ComputerVsComputer =>
          printMatch(randomPlayer("Computer 1"), randomPlayer("Computer 2"))
        case UserVsComputer =>
          val name = in.chooseName()
          val move = in.chooseMove(moves)
          printMatch(Player(name, move), randomPlayer("Computer"))
      }
      in.wantToContinue() match {
        case Continue => start()
        case Exit => println("Thank you for playing")
      }
    }

  }

  class InputParser(in: Reader) {
    val modeSelectionPrompt =
      """
        |Please choose a mode:
        |1.  Human vs Computer
        |2.  Computer vs Computer
      """.stripMargin

    def chooseMode(): GameMode = Console.withIn(in) {
      def chooseModeRec(): GameMode = StdIn.readLine(modeSelectionPrompt) match {
        case "1" => UserVsComputer
        case "2" => ComputerVsComputer
        case _ =>
          println("Error: Please choose 1 or 2")
          chooseModeRec()
      }

      chooseModeRec()
    }

    def wantToContinue(): Action = Console.withIn(in) {
      def wantToContinueRec(): Action = StdIn.readLine("Do you want to continue?(Y/N) ").toUpperCase match {
        case "Y" => Continue
        case "N" => Exit
        case _ =>
          println("Error: please type Y or N")
          wantToContinueRec()
      }

      wantToContinueRec()
    }

    def chooseName(): String = Console.withIn(in) {
      def chooseNameRec(): String = StdIn.readLine("Please, choose a name: ") match {
        case "" => chooseNameRec()
        case name => name
      }

      chooseNameRec()
    }

    def chooseMove(moves: List[Move]): Move = Console.withIn(in) {
      val movesToString = moves.zipWithIndex.map {
        case (m, p) => s"${p + 1}. $m"
      } mkString "\n"
      val moveSelectionPrompt = s"Please select a move:\n$movesToString\n"

      def chooseMoveRec(): Move = StdIn.readLine(moveSelectionPrompt) match {
        case n if n.forall(_.isDigit) && n.toInt > 0 && n.toInt <= moves.size => moves(n.toInt - 1)
        case _ =>
          println("Error!")
          chooseMoveRec()
      }

      chooseMoveRec()
    }
  }

  trait Move

  case object Rock extends Move

  case object Paper extends Move

  case object Scissors extends Move

  trait Moves {

    protected val moves = List(Rock, Paper, Scissors)

    protected val beats: Map[Move, List[Move]]

    protected def randomMove: Move = moves(Random.nextInt(moves.size))

    protected def canBeat(m1: Move, m2: Move) = beats.get(m1).exists(_.contains(m2))

  }

  trait GameMode

  case object UserVsComputer extends GameMode

  case object ComputerVsComputer extends GameMode

  trait Action

  case object Continue extends Action

  case object Exit extends Action

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

  def main(args: Array[String]): Unit = {

  }
}
package state
import scala.util.Random
import java.io.{Reader}
import scala.io.StdIn
/*
https://github.com/nicocavallo/rockpaperscissors-scala/tree/master/src/main/scala/challenge
*/
object RochePapierCiseaux {

  case class Player(name: String, move: Move) {
    override def toString = name
  }
  class GameApp(in: InputParser) {
    self: GameContext =>
    private def printResult(result: GameResult): Unit = result match {
      case Win(player) => println(s"Le gagnant est '$player'")
      case Tie => println("C'est un match nul")
    }

    private def printMatch(p1: Player, p2: Player): Unit = {
      println(s"${p1.name} chose '${p1.move}'")
      println(s"${p2.name} chose '${p2.move}'")
      printResult(play(p1, p2))
    }

    def start(): Unit = {
      println("Commencer une nouvelle partie")
      in.chooseMode() match {
        case ComputerVsComputer =>
          printMatch(randomPlayer("Ordinateur 1"), randomPlayer("Ordinateur 2"))
        case UserVsComputer =>
          val name = in.chooseName()
          val move = in.chooseMove(moves)
          printMatch(Player(name, move), randomPlayer("Ordinateur"))
      }
      in.wantToContinue() match {
        case Continue => start()
        case Exit => println("Merci d'avoir joué!")
      }
    }
  }

  class InputParser(in: Reader) {
    val modeSelectionPrompt =
      """
        |S.v.p choisir un mode:
        |1.  Humain vs Ordinateur
        |2.  Ordinateur vs Ordinateur
      """.stripMargin

    def chooseMode(): GameMode = Console.withIn(in) {
      def chooseModeRec(): GameMode = StdIn.readLine(modeSelectionPrompt) match {
        case "1" => UserVsComputer
        case "2" => ComputerVsComputer
        case _ =>
          println("Erreur: S.v.p choisir 1 ou 2")
          chooseModeRec()
      }
      chooseModeRec()
    }
    def wantToContinue(): Action = Console.withIn(in) {
      def wantToContinueRec(): Action = StdIn.readLine("Voulez-vous continuer?(O/N) ").toUpperCase match {
        case "O" => Continue
        case "N" => Exit
        case _ =>
          println("Erreur: S.v.p choisir O ou N")
          wantToContinueRec()
      }
      wantToContinueRec()
    }
    def chooseName(): String = Console.withIn(in) {
      def chooseNameRec(): String = StdIn.readLine("S.v.p, choisir un nom: ") match {
        case "" => chooseNameRec()
        case name => name
      }
      chooseNameRec()
    }

    def chooseMove(moves: List[Move]): Move = Console.withIn(in) {
      val movesToString = moves.zipWithIndex.map {
        case (m, p) => s"${p + 1}. $m"
      } mkString "\n"
      val moveSelectionPrompt = s"S.v.p, choisir un mouvement:\n$movesToString\n"

      def chooseMoveRec(): Move = StdIn.readLine(moveSelectionPrompt) match {
        case n if n.forall(_.isDigit) && n.toInt > 0 && n.toInt <= moves.size => moves(n.toInt - 1)
        case _ =>
          println("Erreur!")
          chooseMoveRec()
      }
      chooseMoveRec()
    }
  }

  sealed trait Move
  case object Roche extends Move
  case object Papier extends Move
  case object Ciseaux extends Move

  sealed trait Moves {

    protected val moves = List(Roche, Papier, Ciseaux)
    protected val beats: Map[Move, List[Move]]
    protected def randomMove: Move = moves(Random.nextInt(moves.size))
    protected def canBeat(m1: Move, m2: Move) = beats.get(m1).exists(_.contains(m2))
  }

  sealed trait GameMode
  case object UserVsComputer extends GameMode
  case object ComputerVsComputer extends GameMode

  sealed trait Action
  case object Continue extends Action
  case object Exit extends Action

  sealed trait GameContext extends Moves {
    def play(p1: Player, p2: Player): GameResult = p1.move == p2.move match {
      case true => Tie
      case _ if canBeat(p1.move, p2.move) => Win(p1)
      case _ => Win(p2)
    }
    def randomPlayer(name: String) = Player(name, randomMove)
  }

  sealed trait RockPaperScissors extends GameContext {
    override protected val beats: Map[Move, List[Move]] =
      Map(Roche -> List(Ciseaux), Ciseaux -> List(Papier), Papier -> List(Roche))
  }

  sealed trait GameResult
  case object Tie extends GameResult
  case class Win(player: Player) extends GameResult

  def main(args: Array[String]): Unit = {
println(Map(Roche(List(Ciseaux,Papier))))


  }
}
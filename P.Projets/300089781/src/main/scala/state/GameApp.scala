package state

import java.io.{Reader, InputStreamReader}
import scala.io.StdIn

object GameApp {
  def main(args: Array[String]): Unit = {
    val input = new InputParser(new InputStreamReader(System.in))
    val app = args.headOption.find(_ == "extended").fold(new GameApp(input) with RockPaperScissors) { _ =>
      new GameApp(input) with RockPaperScissors
    }
    app.start()
  }
}

class GameApp(in: InputParser) { self:GameContext =>

  private def printResult(result: GameResult): Unit = result match {
    case Win(player) => println(s"The winner is '$player'")
    case Tie => println("It was a Tie")
  }

  private def printMatch(p1:Player, p2:Player): Unit = {
    println(s"${p1.name} chose '${p1.move}'")
    println(s"${p2.name} chose '${p2.move}'")
    printResult(play(p1,p2))
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
      case Exit => println("Merci d'avoir joué")
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

  def chooseMove(moves:List[Move]): Move = Console.withIn(in) {
    val movesToString = moves.zipWithIndex.map {
      case (m,p) => s"${p+1}. $m"
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
trait GameMode

case object UserVsComputer extends GameMode

case object ComputerVsComputer extends GameMode

trait Action

case object Continue extends Action

case object Exit extends Action
package state

import scala.util.Random
import scala.collection.immutable.SortedMap
import scala.io.StdIn
import scala.util.Try
import scalaz.State

sealed trait GameResult
  case object Player1Wins extends GameResult{
    override def toString: String = "Player 1 wins the game!"
  }

  case object Player2Wins extends GameResult{
    override def toString: String = "Player 2 wins the game!"
  }


  case object GameTied extends GameResult{
    override def toString: String = "The game is tied!"
  }


object RochePapierCiseaux {
  def play(player1Gesture: Gesture, player2Gesture: Gesture): GameResult =
    player1Gesture.winsAgainst(player2Gesture) match {
      case _: Wins.type => Player1Wins
      case _: Loses.type => Player2Wins
      case _: GestureTied.type => GameTied
    }
}

case class GameRunner(computerAiGestureChooser: ComputerAiGestureChooser) {
  def playComputerVsComputer(): (GameResult, Gesture, Gesture) = {
    val player1Gesture = computerAiGestureChooser.nextGesture()
    val player2Gesture = computerAiGestureChooser.nextGesture()

    (RochePapierCiseaux.play(player1Gesture, player2Gesture), player1Gesture, player2Gesture)
  }

  def playPlayerVsComputer(player1Gesture: Gesture): (GameResult, Gesture, Gesture) = {
    val player2Gesture = computerAiGestureChooser.nextGesture()
    (RochePapierCiseaux.play(player1Gesture, player2Gesture), player1Gesture, player2Gesture)

  }
}

case class ComputerAiGestureChooser(random: Random) {
  def nextGesture(): Gesture = random.shuffle(Gesture.values).head
}
case object Gesture {
  val values: List[Gesture] = List(Rock, Paper, Scissors)

  lazy val numberToGestureName: SortedMap[Int, String] = {
    val elements = values
      .zipWithIndex
      .map { case (gesture, index) => (index + 1, gesture.toString) }
      .sortBy(_._1)

    SortedMap(elements: _*)
  }

  def findByNumber(number: Int): Gesture = values(number - 1)
}

sealed trait Gesture {
  def winsAgainst(opponentGesture: Gesture): GestureVsGestureResult
}

case object Rock extends Gesture {
  def winsAgainst(opponentGesture: Gesture): GestureVsGestureResult = opponentGesture match {
    case _: Rock.type => GestureTied
    case _: Scissors.type => Wins
    case _: Paper.type => Loses
  }
}

case object Paper extends Gesture {
  def winsAgainst(opponentGesture: Gesture): GestureVsGestureResult = opponentGesture match {
    case _: Rock.type => Wins
    case _: Scissors.type => Loses
    case _: Paper.type => GestureTied
  }
}

case object Scissors extends Gesture {
  def winsAgainst(opponentGesture: Gesture): GestureVsGestureResult = opponentGesture match {
    case _: Rock.type => Loses
    case _: Scissors.type => GestureTied
    case _: Paper.type => Wins
  }
}

sealed trait GestureVsGestureResult
case object Wins extends GestureVsGestureResult
case object Loses extends GestureVsGestureResult
case object GestureTied extends GestureVsGestureResult
case class UI(gameRunner: GameRunner) {

  def play(): Unit = {
    println("\nWelcome to RockPaperScissors!")
    playTwice()
  }

  def playTwice(): Unit = {
    val chooseModeMsg = """
                          |Please choose the game mode you want to play:
                          |1) Player Vs Computer
                          |2) Computer Vs Computer
                          |3) Exit
                          |>""".stripMargin

    retryUntilValidValue(letPlayerChooseIntValueWithMsg(chooseModeMsg), 1, 2, 3) match {
      case Some(gameMode) if gameMode == 1 =>
        playerVsComputer()
        playTwice()
      case Some(gameMode) if gameMode == 2 =>
        computerVsComputer()
        playTwice()
      case Some(gameMode) if gameMode == 3 =>
        println("\nThanks for playing!")
      case None =>
        println("\nInvalid input. Exiting program.")
    }
    def next: State[Int, Option[Int]] =
      State[Int, Option[Int]] {
        case 0 => (0, None)
        case x => (x - 1, Some(x))
      }

    def check: Option[Int] => Boolean = {
      case None    => false
      case Some(x) => /*println(s"$x...");*/ true
    }

    def countDown: State[Int, Boolean] = {
      def go(choice: State[Int, Boolean]): State[Int, Boolean] = choice.flatMap {
        case false => choice
        case true => go(next map check)
      }
      go(next map check)
    }
  }

  def computerVsComputer(): Unit = printGameResult(gameRunner.playComputerVsComputer())

  def playerVsComputer(): Unit = {
    val numberToGestureNames = Gesture.numberToGestureName.foldLeft("") { case (result, (number, gestureName)) =>
      result + s"\n$number) $gestureName"
    }
    val chooseGestureMsg = s"""
                              |Please choose your gesture:$numberToGestureNames
                              |>""".stripMargin

    retryUntilValidValue(letPlayerChooseIntValueWithMsg(chooseGestureMsg),
      Gesture.numberToGestureName.keySet.toSeq: _*) match {
      case Some(player1Gesture) => printGameResult(gameRunner.playPlayerVsComputer
      (Gesture.findByNumber(player1Gesture)))
      case None => println("\nInvalid input. Exiting program.")
    }
  }

  def retryUntilValidValue[T](userInputFunc: => Option[T], validValues: T*): Option[T] = {
    Stream.continually(userInputFunc)
      .flatten // only keep user inputs which got something in them
      .find(userInput => validValues contains userInput) // only keep valid values
  }

  def letPlayerChooseIntValueWithMsg(msg: String): Option[Int] = {
    print(msg)

    for {
      userInput <- Option(StdIn.readLine())
      input <- Try(userInput.toInt).toOption
    } yield input
  }

  def printGameResult(result: (GameResult, Gesture, Gesture)): Unit = {
    val (gameResult, player1Gesture, player2Gesture) = result
    println(s"""
               |Player 1 gesture: $player1Gesture
               |Player 2 gesture: $player2Gesture
               |
        |===> $gameResult""".stripMargin)
  }

  def main(args: Array[String]): Unit = {
    
  }
}

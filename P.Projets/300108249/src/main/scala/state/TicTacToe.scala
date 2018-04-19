package state
import scalaz.State
///BestMoveFinder
trait BestMoveFinder[S <: State[S]] {
  def move(s: S): S
}
///Demo
object Demo extends Application {
  val game = new TicTacToe(
    new HumanTicTacPlayer(),
    new MinMaxStrategyFinder[TicTacToeState]())
  game.play

}
////HumanTictactoe
class HumanTicTacPlayer extends BestMoveFinder[TicTacToeState] {
  def move(s: TicTacToeState): TicTacToeState = {
    println("Input the row and the column, please:")
    val (row, col) = readf2("{0, number} {1,number}")
    val pos = Position(row.asInstanceOf[Long].toInt, col.asInstanceOf[Long].toInt)
    try {
      s.makeMove(pos)
    } catch {
      case _: Throwable => {
        println("Such move is impossible!")
        move(s)}}}
}
//MinMaxStrategie
class MinMaxStrategyFinder[S <: State[S]] extends BestMoveFinder[S] {



  // Here, and in other methods

  // parameter s - is a current state of the game

  def move(s: S): S =

    if(s.isPlayerOneTurn) firstTurn(s).state

    else secondTurn(s).state



  def secondTurn(s: S): Outcome =
    bestMoveFinder(minimize, firstTurn, PLAYER_ONE_WIN, s)

  def firstTurn(s: S): Outcome =
    bestMoveFinder(maximize, secondTurn, PLAYER_ONE_LOOSE, s)

  def bestMoveFinder(strategy: Criteria, opponentMove: S => Outcome,

                     worstOutcome: Int, s: S): Outcome =

    if(s.isGameOver) Outcome(outcomeOfGame(s), s)
    else s.generateStates
      .foldLeft(Outcome(worstOutcome, s)){(acc, state) => {
        val outcome = opponentMove(state).cost
        if(strategy(outcome, acc.cost)) Outcome(outcome, state)
        else acc}}

  def outcomeOfGame(s: S): Int =
    if (s.playerOneWin) PLAYER_ONE_WIN
    else if(s.playerTwoWin) PLAYER_ONE_LOOSE
    else DRAW

  case class Outcome(cost: Int, state: S)
  val PLAYER_ONE_WIN   =  1 // the outcome, when first player wins
  val PLAYER_ONE_LOOSE = -1 // the outcome, when second player wins
  val DRAW             =  0 // the outcome, when nobody wins
  type Criteria = (Int, Int) => Boolean
  def maximize: Criteria = (a, b) => a >= b
  def minimize: Criteria = (a, b) => a <= b
}
////State
trait State[S <: State[S]] {

  def isGameOver      : Boolean
  def playerOneWin    : Boolean
  def playerTwoWin    : Boolean
  def isPlayerOneTurn : Boolean
  def generateStates  : Seq[S]

}

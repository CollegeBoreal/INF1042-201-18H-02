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
////tic tac toe
class TicTacToe(playerOne: BestMoveFinder[TicTacToeState],
                playerTwo: BestMoveFinder[TicTacToeState]) {
  private var players = List(playerOne, playerTwo)
  private var game: TicTacToeState =
    new TicTacToeState(DIMENSION, DIMENSION)
  def play = {
    var moveNumber = 0
    while(!game.isGameOver) {
      println(s"Player ${moveNumber % 2 + 1} makes move:")

      val player = players.head
      game = player.move(game)
      println(display(game))
      players = players.tail :+ player

      moveNumber += 1
    }
    if(game.playerOneWin) println("Player One win!")
    else if(game.playerTwoWin) println("Player Two win!")
    else println("Draw!")
  }
  def display(game: TicTacToeState) =

    (1 to DIMENSION).map(row =>
      (1 to DIMENSION).map(col => {
        val p = Position(row, col)
        if(game.playerOnePositions contains p) X_PLAYER
        else if(game.playerTwoPositions contains p) O_PLAYER
        else EMPTY_CELL
      }).mkString).mkString("\n") + "\n"
  final val DIMENSION  = 3
  final val X_PLAYER   = "X"
  final val O_PLAYER   = "O"
  final val EMPTY_CELL = "."
}
///TicTacToeState
case class Position(val row: Int, val col: Int)



class TicTacToeState(val playerOnePositions : Set[Position],

                     val playerTwoPositions : Set[Position],

                     val availablePositions : Set[Position],

                     val isPlayerOneTurn : Boolean,

                     val winLength : Int) extends State[TicTacToeState] {



  lazy val isGameOver  : Boolean =

    availablePositions.isEmpty || playerOneWin || playerTwoWin



  lazy val playerOneWin: Boolean = checkWin(playerOnePositions)



  lazy val playerTwoWin: Boolean = checkWin(playerTwoPositions)



  def generateStates: Seq[TicTacToeState] =

    for(pos <- availablePositions.toSeq) yield makeMove(pos)



  def makeMove(p: Position): TicTacToeState = {

    assert(availablePositions.contains(p))

    if(isPlayerOneTurn)

      new TicTacToeState(

        playerOnePositions + p,

        playerTwoPositions,

        availablePositions - p,

        !isPlayerOneTurn,

        winLength)

    else new TicTacToeState(
      playerOnePositions,
      playerTwoPositions + p,
      availablePositions - p,
      !isPlayerOneTurn,
      winLength)
  def checkWin(positions: Set[Position]): Boolean =

    directions.exists(winConditionsSatisified(_)(positions))

  final val directions = List(leftDiagonal, rightDiagonal, row, column)

  type StepOffsetGen = (Position, Int) => Position
  def leftDiagonal : StepOffsetGen =

    (pos, offset) => Position(pos.row + offset, pos.col + offset)
  def rightDiagonal : StepOffsetGen =

    (pos, offset) => Position(pos.row - offset, pos.col + offset)
  def row : StepOffsetGen =

    (pos, offset) => Position(pos.row + offset, pos.col)
  private def column : StepOffsetGen =

    (pos, offset) => Position(pos.row, pos.col + offset)
  def winConditionsSatisified(step: StepOffsetGen)
  (positions: Set[Position]): Boolean =
  positions exists( position =>
    (0 until winLength) forall( offset =>
      positions contains step(position, offset)))

  // Just additional constructor for convenience
  def this(dimension: Int, winLength: Int) = this(
    Set(), Set(), // positions of the players are empty initially
    (for{row <- (1 to dimension) // initialize available positions
         col <- (1 to dimension)} yield Position(row, col)).toSet,
    true, winLength)
}
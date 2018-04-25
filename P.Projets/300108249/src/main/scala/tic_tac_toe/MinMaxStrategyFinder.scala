package tic_tac_toe

class MinMaxStrategyFinder[S <: State[S]] extends BestMoveFinder[S] {
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
  val PLAYER_ONE_WIN   =  1

  val PLAYER_ONE_LOOSE = -1

  val DRAW             =  0
  type Criteria = (Int, Int) => Boolean
  def maximize: Criteria = (a, b) => a >= b
  def minimize: Criteria = (a, b) => a <= b
}
//to do prof voit rien
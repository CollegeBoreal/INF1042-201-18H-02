package tic_tac_toe

object Demo extends Application {
  val game = new TicTacToe(
    new HumanTicTacPlayer(),
    new MinMaxStrategyFinder[TicTacToeState]())
  game.play
}
//to do prof voit rien
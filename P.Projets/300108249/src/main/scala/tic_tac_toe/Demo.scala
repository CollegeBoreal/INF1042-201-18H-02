package tic_tac_toe

object Demo{
    val game = new TicTacToe(
      new HumanTicTacPlayer(),
      new MinMaxStrategyFinder[TicTacToeState]())
    game.play
  def main(args: Array[String]): Unit = {


  }

}
//to do prof voit rien
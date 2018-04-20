package tic_tac_toe

object Demo{
    val game = new TicTacToe(
      new HumanTicTacPlayer(),
      new MinMaxStrategyFinder[TicTacToeState]())
    game.play
  def main(args: Array[String]): Unit = {
    println("")

    println(" - - - - - - - - - ")

    println(" Tic tac toe ")

   

  }

}
//to do prof voit rien
package tic_tac_toe



trait BestMoveFinder[S <: State[S]] {
  def move(s: S): S

}
//to do prof voit rien
package state

import scalaz.State

object Counter {

  def next: State[Int, Option[Int]] =
    State[Int, Option[Int]] {
      case 5 => (5, None)
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
  def main(args: Array[String]): Unit = {

  }

}

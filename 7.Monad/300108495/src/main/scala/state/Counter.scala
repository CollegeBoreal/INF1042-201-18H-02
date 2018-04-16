package state

import scalaz.state

object Counter {

  // A simple counter using the state monad. The counter counts down by one each
  // iteration, and when it hits zero it stops. The value (Option[Int])
  // indicates if we should continue or stop processing.
  def next: state[Int, Option[Int]] =
    state[Int, Option[Int]] {
      case 0 => (0, None)
      case x => (x - 1, Some(x))
    }

  // Process the input and indicate if we should continue processing
  def check: Option[Int] => Boolean = {
    case None    => false
    case Some(x) => /*println(s"$x...");*/ true
  }

  // Loop until the condition is false
  def countDown: state[Int, Boolean] = {
    def go(choice: state[Int, Boolean]): state[Int, Boolean] = choice.flatMap {
      case false => choice
      case true => go(next map check)
    }
    go(next map check)
  }

  def main(args: Array[String]): Unit = {
    assert(countDown.run(4)==(0,false))
    assert(countDown.exec(5)==0)
    assert(!countDown.eval(3))
  }

}

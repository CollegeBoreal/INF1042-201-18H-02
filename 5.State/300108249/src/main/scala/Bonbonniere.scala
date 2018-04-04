sealed trait Input
case object Coin extends Input
case object Turn extends Input

case class Machine(locked: Boolean, candies: Int, coins: Int)

import State._
object Bonbonniere {
<<<<<<< HEAD
  // pattern matching for State
  def updateRule: Input => ( Machine => Machine) = (i: Input) => (s: Machine) => (i, s) match {
    case (Turn,Machine(true,_,_)) => s
  }

  def simulateMachine(inputs: List[Input]): State[Machine, (Int,Int)] =
    for {
      _ <- sequence(inputs.map(modify[Machine] _ compose updateRule))
      s <- get
    } yield (s.coins, s.candies)// a besoin de sequence

  def main(args: Array[String]): Unit = {
    assert(simulateMachine(List(Turn)).run(Machine(locked = true, 1, 0))._1==(0,1))

  }
}
=======

  // pattern matching for State

  def simulateMachine(inputs: List[Input]): State[Machine, (Int,Int)] = ??? // a besoin de sequence

  def main(args: Array[String]): Unit = {

    assert(simulateMachine(List(Coin,Turn)).run(Machine(locked = true, 1, 0))==(1,1))

  }

}
>>>>>>> 633adc0ca8c393ea3a383b90b4c46a424f4fa947

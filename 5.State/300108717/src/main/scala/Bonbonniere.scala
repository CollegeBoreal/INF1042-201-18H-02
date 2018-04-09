
sealed trait Input

case object Coin extends Input

case object Turn extends Input



case class Machine(locked: Boolean, candies: Int, coins: Int)

import State._

object Bonbonniere {
  //pattern matcing for State

  def updateRule: Input => (Machine => Machine ) = (i: Input) => (s: Machine)=> (i, s) match {
    case (Turn,Machine(true,_,_)) => s
  }

  //sugarized
  def simulateMachine(inputs: List[Input]): State[Machine, (Int,Int)]=
    for {
      _ <- sequence(inputs.map(modify[Machine] _ compose updateRule))
      s <- get
    } yield (s.coins, s.candies)


  def main(args: Array[String]): Unit = {


    assert(simulateMachine(List(Turn)).run(Machine(locked = true, 1, 0))._1 ==(0,1))


  }


}

import State._

sealed trait Input
case object Coin extends Input
case object Turn extends Input
case class Machine(locked: Boolean, candies: Int, coins: Int)


object Bonbonniere {
def updateRule: Input => (Machine => Machine) = (i: Input) => (s: Machine) => (i, s) match {
  case (_, Machine( _,0, _)) => s
  case (Turn, Machine(true, _, _)) => s
case (Coin, Machine(true, candy, coin)) => Machine(locked = false, candy, coin + 1)
case (Turn, Machine(false, candy, coin))=> Machine(locked = true, candy -1, coin)
}

  def simulateMachine(inputs: List[Input]): State[Machine, (Int, Int)]=
    for {
      _ <- sequence(inputs.map(modify[Machine] _ compose updateRule))
      s <- get
    } yield (s.coins, s.candies)

  def main(args: Array[String]): Unit = {

    assert(simulateMachine(List(Turn)).run(Machine(locked = true, 1, 0))._1==(0, 1))
    assert(simulateMachine(List(Coin, Turn)).run(Machine(locked = true, 1, 0))._1==(1, 0))
    assert(simulateMachine(List(Coin, Turn, Coin)).run(Machine(locked = true, 1, 0))._1==(1, 0))
  }
}

sealed trait Input
case object Coin extends Input
case object Turn extends Input

case class Machine (locked: Boolean, candies: Int, coins: Int)

  object Bonbonniere {
    def simulateMachine(inputs: List[Input]): State[Machine, (Int,Int)] = ???
    def main(args: Array[String]): Unit = {
        assert(simulateMachine(List(Coin,Turn)).run(Machine(locked = true, 1, 0))==(1, 1))
      }
  }

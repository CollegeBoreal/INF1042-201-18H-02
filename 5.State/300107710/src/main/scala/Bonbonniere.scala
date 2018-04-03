

sealed trait Input
case object Coin extends Input
case object Turn extends Input

case class Machine(locket: Boolean, candies: Int, coins: Int)

object Bonbonniere {

  def simulateMachine(inputs: List[Input]): State[Machine, (Int, Int)] = ??? // sequence

  def main(args: Array[String]): Unit = {
    assert(simulateMachine(List(Coin,Turn)).run(Machine(locked = true, 1, 0)) == (1,1))

  }

}
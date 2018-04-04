sealed trait Imput
case object Coin extends Imput
case object Turn extends Imput

case class Machine(locked: Boolean, candies: Int, coins: Int)

object Bonbonniere {

  def simulateMachine(imputes: List[Imput]): State[Machine, (Int, Int)] = ??? // a besoin de sequence

  def main(args: Array[String]): Unit = {
    def simulateMachine(inputs: List[Input]): State[Machine, (Int,Int)] = ???
    def main(args: Array[String]): Unit = {
      assert(simulateMachine(List(Coin,Turn)).run(Machine(locked = true, 1, 0))==(1, 1))
    }
  }
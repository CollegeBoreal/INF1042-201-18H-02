
sealed trait Input
case object Coin extends Input
case object Turn extends Input

case class Machine(locked: Boolean, candies: Int, coins: Int)

object Candy {

def updateRule: Input => ( Machine => Machine) = ( i: Input) => (s: Machine) => (i, s) match {
  case (Turn, Machine(true, _, _)) => s
  case(_, Machine(_, 0, _))  => s
  case (Coin, Machine(true, 1, 0)) =>
    Machine(false, 1, 1)
  case (Turn, Machine(false, 1, 1)) => Machine(true, 0, 1)

}
   import scalaz.{State,Applicative}
   import scalaz.State.{get, modify}
   import scalaz.std.list._

   def simulateMachine(inputs: List[Input]): State[Machine, (Int, Int)] =
    for {
    _ <- Applicative[({type CandyState[A] = State[Machine,A]})#CandyState].sequence(imputs.map(modify[Machine]_compose updateRule))
    s <- get
  } yield (s.coins, s.candies)


    def main(args: Array[String]): Unit = {
}
}
# Candy Dispenser

## Create a Scala Script called Candy in src/main/scala

```scala
package dispenser

sealed trait Input
case object Coin extends Input
case object Turn extends Input

case class Candy(locked: Boolean, candies: Int, coins: Int)

object Candy {

  def withRule(i: Input): Candy => Candy = (s: Candy) => (i, s) match {
    case (_, Candy(_, 0, _))        => s
    case (Coin, Candy(false, _, _)) => s
    case (Turn, Candy(true, _, _))  => s
    case (Coin, Candy(true, candy, coin)) => Candy(locked = false, candy, coin + 1)
    case (Turn, Candy(false, candy, coin)) => Candy(locked = true, candy - 1, coin)
  }

}
```

## Create a Scala Script called CandySpec in test/main/scala

```scala
package dispenser

import org.scalatest.FunSpec

import scalaz.State.{get, modify}
import scalaz.{Applicative, State}
import scalaz.std.list._

class CandySpec extends FunSpec {

  def simulate(inputs: List[Input]): State[Candy, (Int, Int)] =
    for {
      _ <- Applicative[({type f[A] = State[Candy,A]})#f].sequence(inputs.map(modify[Candy] _ compose Candy.withRule))
      s <- get
    } yield (s.coins, s.candies)


  describe("When evaluating run") {
    it("should inserting a coin into a locked machine cause it to unlock if there’s any candy left.") {
      val (state1, a) = simulate(List(Coin)).run(Candy(locked = true, 1, 0))
      assert(!state1.locked)
      assert(a == (1, 1)) // $1, 1 candy left
    }
  }
  
}
```


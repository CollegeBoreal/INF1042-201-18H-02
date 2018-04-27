
package state

//      Factoriel

//      src/main/scala/progscala2/typelessdomore/factorial-tailrec.sc

import scalaz.State
import scala.annotation.tailrec
import scalaz.std.long

object Factoriel {

  type StateFac = State[Int, Long]

  def factorial: StateFac = State { n =>
    if (n == 0)
      (n, 1)
    else
      (n, n * factorial.eval(n - 1))
  }


  def main(args: Array[String]): Unit = {
    (0 to 2) foreach (i =>
      println(factorial.eval(i)))
    assert(factorial.eval(0) == 1)
    assert(factorial.eval(1) == 1)
    assert(factorial.eval(4) == 24)
    assert(factorial.eval(5) == 120)
    assert(factorial.eval(6) == 720)
    assert(factorial.eval(7) == 5040)
    assert(factorial.eval(8) == 40320)
    assert(factorial.eval(9) == 362880)
    assert(factorial.eval(10) == 3628800)
    assert(factorial.eval(11) == 39916800)
    assert(factorial.eval(12) == 479001600)


  }
}


package state

//      Factoriel

//      src/main/scala/progscala2/typelessdomore/factorial-tailrec.sc

import scalaz.State
import scala.annotation.tailrec
import scalaz.std.long

object Factoriel {

  type StateFac = State[Int, Long]

  def factorial: StateFac = State{ n =>
    if (n == 0)
      (n,1)
    else
      (n,n * factorial.eval(n - 1))
  }


  def main(args: Array[String]): Unit = {
    (0 to 5) foreach ( i => println(factorial.eval(i)) )
  }
}


package state

//      Factoriel

//      src/main/scala/progscala2/typelessdomore/factorial-tailrec.sc

import scalaz.State
import scala.annotation.tailrec
import scalaz.std.long

object Factoriel {

  type StateFac = State[Int, Long]

  def factorial: StateFac =State { j =>

    def fact(accumulator: Int):StateFac = State {fact =>
      if (j <= 1)(long, 1)
      else (fact(j * accumulator),(j - 1))
    }


    fact(1,j)



  }

  def main(args: Array[String]): Unit = {
   // (0 to 5) foreach ( i => println(factorial(i)) )
  }
}

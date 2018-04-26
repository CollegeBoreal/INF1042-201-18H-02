package state


/*
 Exemple d un jeux de devinette sur scala avec State monad.
     https://gist.github.com/tux2323/1362638             */

import scala.util._
import scalaz.State



object JeuxDevinette {
  type StateNumber = State[Int,Int]
  var smallest = 0
  var biggest = 100
  val help = "You can enter the following commands : smaller, bigger or exit"

  println("Guess a number between " + smallest + " and " + biggest)
  println("Are you ready  press enter")
  readLine()

  println(help)

  def count (State:Int ): StateNumber = State { number =>
    def go(acc: Int): Int => Int = { guess =>

      if (acc == 0) {
        println("Is your number:" + help)
        0
      } else {
        println("Is your number : " + guess)
        readLine() match {
          case "smaller" => smaller
          case "bigger" => bigger
          case "exit" => sys.exit
          case "?" => println(help)
          case x => println("Unknown option: '" + x + "'")
        }

        def smaller = biggest = guess

        def bigger = smallest = guess

        def nextGuess = (smallest + biggest) / 2

        go(acc - 1)(nextGuess)
      }

    }

    go(8)(number)
  }

  def main(args: Array[String]): Unit = {
    var guess = new Random().nextInt(biggest)
    count(guess)

  }

}


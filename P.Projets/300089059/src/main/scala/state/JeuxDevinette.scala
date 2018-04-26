package state

/*
Exemple d un jeux sur scala avec State monad.

  https://gist.github.com/tux2323/1362638
         */

import scala.util._



object Game {
  var smallest = 0
  var biggest = 100
  val number= 25
  val guess = new Random().nextInt(biggest)
  val help = "You can enter the following commands : smaller, bigger or exit"

  println("Guess a number between " + smallest + " and " + biggest)
  println("Is your number:" + guess)
  println("You can enter the following commands : smaller, bigger or exit: ")

  def main(args: Array[String]): Unit = {
    val help = "You can enter the following commands : smaller, bigger or exit"

  }
  def count(number: Int): Int = {
    def go(guess: Int): Int = {

      if (guess = number) {

        println("Felicitation")

        )
        100
      if (guess => number)

      } else {
        println("Is your number:" + guess)

        }

        def smaller = guess

        def bigger = guess

        def nextGuess = (smallest + biggest) / 2

        go(nextGuess)
      }

    }

    go(number)
  }


}

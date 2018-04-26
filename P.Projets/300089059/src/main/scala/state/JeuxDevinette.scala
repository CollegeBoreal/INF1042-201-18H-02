package state


/*
 Exemple d un jeux sur scala avec State monad.

     https://gist.github.com/tux2323/1362638             */

import scala.util._



object Game {
  var smallest = 0
  var biggest = 100
  val guess = new Random().nextInt(biggest)
  val help = "You can enter the following commands : smaller, bigger or exit"

  println("Guess a number between " + smallest + " and " + biggest)
  println("Are you ready  press enter")
  readLine()

  println(help)


  def main(args: Array[String]): Unit = {
    count("number":Int)

  }
  def count(number: Int): Int = {
    def go(guess: Int, acc: Int): Int = {

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

        go(nextGuess, acc - 1)
      }

    }

    go(0, number)
  }


}




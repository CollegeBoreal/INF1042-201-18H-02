package state
/* */
import scala.util._

object Game extends App
{
  val smallest = 0;
  val biggest = 100;
  val guess = new Random().nextInt(biggest)
  val help = "You can enter the following commands : smaller, bigger or exit"

  println("Guess a number between " + smallest + " and " + biggest)
  println("Are you ready please press enter")
  readLine()

  println(help)
  while (true)
  {
    println("Is your number : " + guess)
    readLine() match
    {
      case "smaller" => smaller
      case "bigger" => bigger
      case "exit" => sys.exit
      case "?" => println(help)
      case x => println("Unknown option: '" + x + "'")
    }
    guess = nextGuess
  }

  def smaller = biggest = guess

  def bigger = smallest = guess;

  def nextGuess = (smallest + biggest) / 2

}

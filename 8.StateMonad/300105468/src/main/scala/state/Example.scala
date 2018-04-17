
package state

import scala.language.higherKinds._

// Call Example.example.run to see the example running
//https://www.snip2code.com/Snippet/708340/Example-of-monadic-loop-using-the-State
object Example {
  type MyState[A] = State[Int, A]

  // A simple counter using the state monad. The counter counts down by one each
  // iteration, and when it hits zero it stops. The value (Option[Int])
  // indicates if we should continue or stop processing.
  def extractNextItem: MyState[Option[Int]] =
    State { counter =>
      if(counter == 0)
        (0, None)
      else
        (counter-1, Some(counter))
    }

  // Process the input and indicate if we should continue processing
  def process(in: Option[Int]): Boolean =
    in match {
      case None    => false
      case Some(x) =>
        println(s"Value is $x")
        true
    }

  // Loop until the condition is false
  def loop(in: MyState[Boolean]): MyState[Boolean] = {
    val choice: MyState[Boolean] = extractNextItem map process
    choice flatMap { bool =>
      if(bool)
        loop(choice)
      else
        choice
    }
  }

  def main(args: Array[String]): Unit = {
    println(loop(extractNextItem map process).run(5))
  }

}

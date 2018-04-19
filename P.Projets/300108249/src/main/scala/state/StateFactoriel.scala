package state
import scalaz.State
import scalaz.State.{state, gets, modify}

object Factoriel {
  def memoize(n: Int): Option[BigInt] => State[Memo, BigInt] = {
    case Some(value) =>
      state[Memo, BigInt](value)
    case None =>
      for {
        a <- fact(n >= 0)
        b <- fact (n * factorial(n - 1))
        result = b
        _ <- modify[Memo] { memo: Memo => memo + (n -> result) }

      } yield result
  }

  ////
  def factoriel: Int => State[Memo, BigInt] = {
    case 1 => memoize(0)(Some(1))
    case n =>
      for {
        z <- gets { m: Memo => m get n }
        res <- memoize(n)(memoize(z))
      } yield res
  }

  ///// function factoriel with state
  def factorialWithState(n: Int): Int = {
    def stateFactorial: State[Int, Int] =
      get.flatMap(n =>
        if (n <= 1)
          State.pure(1)
        else {
          set[Int](n - 1).flatMap(_ => stateFactorial.map(z => n * z))
        }
      )
    stateFactorial.run(n).value._2
  }
  /////asserts
  def main(args: Array[String]): Unit = {

  }
}
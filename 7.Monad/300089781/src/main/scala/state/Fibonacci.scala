package state

import scalaz.State
import scalaz.State.{state, gets, modify}

/*
https://gist.github.com/mpilquist/5025874
 */
object Fibonacci {

  type Memo = Map[Int, BigInt]

  def memoize(n: Int): Option[BigInt] => State[Memo, BigInt] = {
    case Some(value) =>
      state[Memo, BigInt](value) // store to state
    case None =>
      for {
        a <- fib(n - 2)
        b <- fib(n - 1)
        result = b + a // add previous one and previous two
        _ <- modify[Memo] { memo: Memo => memo + (n -> result) } // update state
      } yield result
  }

  def fib: Int => State[Memo, BigInt] = {
    case 0 => memoize(0)(Some(0))
    case 1 => memoize(0)(Some(1))
    case n =>
      for {
        m <- gets { m: Memo => m get n }  // retrieve from state
        res <- memoize(n)(m)
      } yield res
  }

  def main(args: Array[String]): Unit = {
    assert(fib(2).eval(Map.empty)==1)
    assert(fib(9).eval(Map.empty)==34)
    assert(fib(80).eval(Map.empty)==BigInt("23416728348467685"))
    assert(fib(100).eval(Map.empty)==BigInt("354224848179261915075"))
  }

}

package state
import scalaz.State
import scalaz.Scalaz._

case class LeftOver(size: Int)
object LeftOver{
  type Result[A] = State[LeftOver, A]
  def getFromState(a: Int): Result[Int] = {
    State[LeftOver, Int] {
      case x => (LeftOver(x.size - a), a)
    }
  }
  def addToState(a: Int): Result[Int] = {
    State[LeftOver, Int] {
      case x => (LeftOver(x.size + a), a)
    }
  }

  def main(args: Array[String]): Unit = {
    val res: Result[Int] =
      for {
      - <- addToState(20)
      - <- getFromState(5)
      - <- getFromState(5)
      b <- getFromState(5)
    } yield b
    assert(res(LeftOver(10))== (LeftOver(15), 5))

  }
}

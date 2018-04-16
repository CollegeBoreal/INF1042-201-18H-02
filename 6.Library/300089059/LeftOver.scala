
import scalaz.State
import scalaz.Scalaz

case class LeftOver(size: Int)

object LeftOver {

  type Result[A] = State[LeftOver, A]

  def getFromState(a: Int): Result[Int]= {
    State[LeftOver, Int] {
      case x => (LeftOver(x.size - a), a)
    }
  }

  def addToState(a: Int): Result[Int] ={
    State[LeftOver, Int] {
      case x => (Left0ver(x.size + a), a)
    }
  }

  def main(args: Array[String]): Unit = {
    val res: Result[Int]
    for {
      _ <- addToState(20)
      _<-getFromState(5)
      _<-getFromState(5)
     b _<-getFromState(5)
    } yield b

    println(res(LeftOver(10)))
    assert(res(LeftOver(10))==(LeftOver(15), 5))
  }
}

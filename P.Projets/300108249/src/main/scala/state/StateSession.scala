package state

import scalaz._
import scalaz.State._

case class Session(version: Int = 0)

object StateSession {


  def func1(arg: String) = State((x: Session) => (x.copy(version = x.version + 1), arg + "-" + x.version))
  val composedFunction1: State[Session, String] = for {

    _ <- init
    _ <- func1("joe")
    _ <- modify { s: Session => Session(100) }
    _ <- func1("alice")
    _ <- put(Session(200))
    x <- func1("nathan")
  } yield x

  println("composedFunction1: " + composedFunction1(Session(0)))

  def funcReturnInt(arg: String): State[Session, Int] =
    State((x: Session) => (x.copy(version=x.version+1), arg.toInt))

  def funcReturnStringNeedsInt(arg: String, i: Int): State[Session, String] =
    State((x: Session) => (x.copy(version=x.version+1), arg + "- " + x.version + "." + i))

  def funcReturnString(arg: String): State[Session, String] =
    State((x: Session) => (x.copy(version=x.version+1), arg + "-" + x.version))

  val composedFunction2 = for {

    _ <- put(Session(0))
    _ <- func1("joe")
    i <- funcReturnInt("30")
    x <- funcReturnStringNeedsInt("alice", i)
  } yield x


  println("composedFunction2: " + composedFunction2(null))


  def composedFunction1and2 = for {

    _ <- init[Session]
    _ <- composedFunction1
    x <- composedFunction2
  } yield x


  println("composedFunction1and2: " + composedFunction1and2(Session(0)))


  val composedFunction3 = for {
    _ <- init[Session]

    v <- funcReturnString("session")
    _ = println("Value returned was: " + v)
  } yield v

  println("composedFunction3: " + composedFunction3(Session(10)))


  val composedFunction4 = for {
    _ <- init[Session]
    _ <- funcReturnString("session")
    s <- get[Session]

  _ = println("This should print the state, because the state was turned into a value: " + s)
  } yield s

  println("composedFunction4: " + composedFunction4(Session(10)))



  val composedFunction5 = for {
    _ <- init[Session]

    s <- gets{ s: Session => s.version }
    _ = println("This should print the original input state's version value. The new value is in the value position: " + s)
  } yield s

  println("composedFunction5: " + composedFunction5(Session(10)))

  def funcUsesIntState: State[Int, String] = State( (x: Int) => (x+42, "blah-"+x))
  val composedFunction6 = for {
    _ <- init[Session]
    v <- gets{ s: Session => s.version }
    _ <- iPut[Session, Int](v)
    _ <- funcUsesIntState
    _ <- iModify { x: Int => Map("version" -> x) }
    s <- State((m: Map[String, Int]) => (m, "last_value-" + m("version")))
  } yield s
  println("composedFunction6: " + composedFunction6(Session(10)))



  def main(args: Array[String]): Unit = {

  }
}






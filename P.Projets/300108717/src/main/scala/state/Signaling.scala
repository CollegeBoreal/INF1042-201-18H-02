package state

    /*    http://timperrett.com/2013/11/25/understanding-state-monad
          Exemple of traffic light with scala and Statemonad */


object Signaling {

  sealed trait Aspect

  case object Green extends Aspect

  case object Amber extends Aspect

  case object Red   extends Aspect



  sealed trait Mode

  case object Off      extends Mode

  case object Flashing extends Mode

  case object Solid    extends Mode



  case class Signal(isOperational: Boolean, display: Map[Aspect, Mode])


  object Signal {


    import scalaz.State, State._

    type ->[A,B] = (A,B)

    type SignalState[A] = State[Signal,A]



    val default = Signal(

      isOperational = false,

      display = Map(Red -> Flashing, Amber -> Off, Green -> Off))




    def enable: State[Signal, Boolean] =

      for {

        a <- init

        _ <- modify((s: Signal) => s.copy(isOperational = true))

        r <- get

      } yield r.isOperational




    def halt  = change(Red -> Solid, Amber -> Off,   Green -> Off)

    def ready = change(Red -> Solid, Amber -> Solid, Green -> Off)

    def go    = change(Red -> Off,   Amber -> Off,   Green -> Solid)

    def slow  = change(Red -> Off,   Amber -> Solid, Green -> Off)



    private def change(seq: Aspect -> Mode*): State[Signal, Map[Aspect, Mode]] =

      for {

        m <- init

        _ <- modify(display(seq))

        signal <- get

      } yield signal.display



      private def display(seq: Seq[Aspect -> Mode]): Signal => Signal = signal =>

      if(signal.isOperational)

        signal.copy(display = signal.display ++ seq.toMap)

      else default

  }


  def main(args: Array[String]): Unit = {

    import Signal._

    import scalaz.State.{get => current}



    val program = for {

      _  <- enable

      r0 <- current // debuggin

      _  <- halt

      r1 <- current // debuggin

      _  <- ready

      r2 <- current // debuggin

      _  <- go

      r3 <- current // debuggin

      _  <- slow

      r4 <- current

    } yield r0 :: r1 :: r2 :: r3 :: r4 :: Nil



    val t = program.eval(default)
    t.zipWithIndex.foreach { case (v,i) =>

      println(s"r$i - $v")



    }

    val programm1 = for {
      _ <- enable
      r0 <- current
    } yield r0 :: Nil

    val programm2 = for {
      _ <- halt
      r1 <- current
    } yield r1 :: Nil


    val t1 = programm1.eval(default)
    val r2 = List(Signal(true,Map(Red -> Solid, Amber -> Off, Green -> Off)))
   // val t3 = programm3.eval(default)
    val r3 = List(Signal(true,Map(Red -> Solid, Amber -> Solid, Green -> Off)))
    val r4 = List(Signal(true,Map(Red -> Off, Amber -> Solid, Green -> Solid)))
    val r5 = List(Signal(true,Map(Red -> Off, Amber -> Solid, Green -> Off)))

    assert(t1== List(Signal(true,Map(Red -> Flashing, Amber -> Off, Green -> Off))))
    assert(r2== List(Signal(true,Map(Red -> Solid, Amber -> Off, Green -> Off))))

    println(r2)
    assert(r3== List(Signal(true,Map(Red -> Solid, Amber -> Solid, Green -> Off))))
    assert(r4== List(Signal(true,Map(Red -> Off, Amber -> Solid, Green -> Solid))))
    assert(r5== List(Signal(true,Map(Red -> Off, Amber -> Solid, Green -> Off))))

    assert(t==List(Signal(true,Map(Red -> Flashing, Amber -> Off, Green -> Off)), Signal(true,Map(Red -> Solid, Amber -> Off, Green -> Off)), Signal(true,Map(Red -> Solid, Amber -> Solid, Green -> Off)), Signal(true,Map(Red -> Off, Amber -> Off, Green -> Solid)), Signal(true,Map(Red -> Off, Amber -> Solid, Green -> Off))))


  }

}
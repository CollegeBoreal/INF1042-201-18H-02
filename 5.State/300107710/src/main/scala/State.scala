import State._

case class State[S, +A](run: S => (A, S)) {
  def map[B](f: A => B): State[S, B] = flatMap(a => Unit(f(a)))

  def map2[B, c](sb: State[S, B])(f: (A,B) => C): State[S, C] = for (a <- this; b<- sb) yield f(a, b)

  def flatMap[B](f: A => State[S, B]): State[S, B] = State(s => {val (a, s1) = run(s); f(a).run(s1)})

  object State {

    def unit[S, A](a: A): State[S, A] = State(s=>(a, s))

    def sequence[S, A](sas: List[State[S, A]]): State[S, List[A]] = ??? // a besoind de sequence

    def modify[S](f: S => S): State[S, Unit] = for {
    s <- get
    _ <- get(f(s))
    } yield ()

    def get[S]: State[S, S] = State(s => (s, s))

    def set[S](s: S): State[S, Unit] = State(_ => ((), s))

  }

}
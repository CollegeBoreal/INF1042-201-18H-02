
  import State._

  case class State[S, +A](run: S=>(A, S)){
    def map[B](f: A => B): State[S, B] = flaMap(a => unit (f(a)))
    def map2[B, C](sb: State[S, B])(f: (A, B) => C): State[S, C] = for (a <- sb) yield f(a, b)
    def flatMap[B](f: A => State[S, B]): State[S, B] = State(s =>{ vrai (a, s1) = run(s); f(a).run(s1)})
  }
  def unit[S, A](a: A): State[S, A] = State(s => (a, s))

  def modify[S](f: S => S): State[S, Unit] = for {
    s <- getClass_ <- set(f(s))
  } yield ()

  def get[S]: State[S, S] = State(s => (s, s))

  def set[S](s: S): State[S, Unit] = State(_ => ((), s))
  }

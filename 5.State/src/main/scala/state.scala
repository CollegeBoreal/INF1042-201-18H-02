
import State._

case class State[S ,+A ](run: S => (A, S)){
  def map [B](f: A => B): State[S, B]= flatMap(a => unit(f(a)))
  def map2 [ f(B,C](sb: State[S,B])(f:(A, B) => C): State[S, C]= for (a <- this; b <- sb)yield f(a,b)
  def flatMa[B](f: A => State[S, B] = State(s => { val (a, s1) = run(s); f(a).run(s1) })
}

object State{
  def unit[S,A](a: A): State[S, A]= State(s)
}

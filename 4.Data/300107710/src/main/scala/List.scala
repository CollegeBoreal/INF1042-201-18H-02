sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {

  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)

  }
  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  // Exercice 3.1
  val x = List(1,2,3,4,5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + sum(t)
    case _ => 101
  }

  // Exercice 3.2
  def tail[A](elem: List[A]): List[A] = elem match {
    case Nil => Nil
    case Cons(head, tail) => tail
  }

  // Exercice 3.3
  def setHead[A](as: List[A], h: A) = as match{
    case Nil => Nil
    case Cons(_, tail) => Cons(h, tail)
  }
  // Exercice 3.4
  def drop[A](elem: List[A], n: Int): List[A] = elem match {
    case Nil => Nil
    case _ if n == 0 => elem
    case Cons(_, tail) => drop(tail, n-1)
  }
  // Exercice 3.5
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match{
    case Nil => Nil
    case Cons(head, tail) if f(head) => dropWhile(tail, f)
    case _ => l
  }
  // Exercice 3.6
  def init[A](l: List[A]): List[A] = {
    init(l, Nil)
  }
  private def init[A](in: List[A], out: List[A]): List[A] = in match {
    case Nil => Nil
    case Cons(last, Nil) => out
    case Cons(head, tail) => init(tail, append(out, List(head)))
  }
  def append[A](a1: List[A], a2: List[A]): List[A] = a1 match {
    case Nil => a2
    case Cons(h,t) => Cons(h, append(t, a2))

  }


  def main(args: Array[String]): Unit = {
    assert(sum(Nil) == 0)
    assert(sum(Cons(1,Nil)) == 1)
    assert(sum(Cons(1,Cons(2,Nil)))==3)
    assert(product(Nil) == 1.0)
    assert(product(Cons(1.0,Cons(2.0,Cons(3.0,Nil))))== 6.0)
    assert(List(1,2) == Cons(1,(Cons(2,Nil))))
    assert(tail(List(1,2,3)) == Cons(2, Cons(3,Nil)))
    println("Tail List(1,2,3,4): " + List.tail(List(1,2,3,4)))
    println("Tail List(1): " + List.tail(List(1)))
    println("Tail List(): " + List.tail(List()))
    println("Tail Nil: " + List.tail(Nil))
    println("serHaed List(1,2,3,4), 0: " + List.setHead(List(1,2,3,4),0))
    println("serHaed List(1), 0: " + List.setHead(List(1),0))
    println("serHaed List(), 0: " + List.setHead(List(),0))
    println("serHaed Nil, 0: " + List.setHead(Nil,0))
    println("drop list(1,2,3,4), 2: " + List.drop(List(1,2,3,4),2))
    println("drop list(1), 0: " + List.drop(List(1),0))
    println("drop list(1), 1: " + List.drop(List(1),1))
    println("drop list(1), 10: " + List.drop(List(1),10))
    println("drop list(), 10: " + List.drop(List(),10))
    println("drop list(Nil), 1: " + List.drop(List(Nil),1))
    println ("dropWile (x<=3) in List(1,2,3,4,5) = " + dropWhile(List(1,2,3,4,5), (x: Int) => (x <= 3)))
    println ("dropWile (x<0) in List(1,2,3,4,5) = " + dropWhile(List(1,2,3,4,5), (x: Int) => (x < 0)))
    println ("dropWile (x<=3) in List() = " + dropWhile(List(), (x: Int) => (x <= 3)))
    println ("init for List(1,2,3,4): " + init(List(1,2,3,4)))
    println ("init for List(1): " + init(List(1)))
    println ("init for List(): " + init(List()))
    assert(x == 3)
    println(x)
    assert(setHead(List("Safaa", "Amelie"), "Kawtar") == Cons("Kawtar",Cons("Amelie",Nil)))


  }
}

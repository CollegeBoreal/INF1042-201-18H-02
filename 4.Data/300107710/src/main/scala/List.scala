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
    case Cons(x, ds) => x * product(ds)
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
  def drop[A](xs: List[A], n: Int): List[A] = xs match {
    case Nil => Nil
    case _ if n == 0 => xs
    case Cons(_, tail) => drop(tail, n-1)
  }
  // Exercice 3.5
  def dropWhile[A](xs: List[A], p: A => Boolean): List[A] = xs match {
    case Nil => Nil
    case Cons(h, t) if (p(h)) => Cons(h,dropWhile(t,p))
    case Cons(h, t) => dropWhile(t, p)
  }
  // Exercice 3.6
  def init[A](xs: List[A]): List[A] = xs match {
    case Nil => sys.error("Init of empty list")
    case Cons(_, Nil) => Nil
    case Cons(h, t) => Cons(h, init(t))

  }


  def main(args: Array[String]): Unit = {
    assert(sum(Nil) == 0)
    assert(sum(Cons(1,Nil))== 1)
    assert(sum(Cons(1,Cons(2,Nil)))==3)
    assert(product(Nil) == 1.0)
    assert(product(Cons(1.0,Cons(2.0,Cons(3.0,Nil))))== 6.0)
    assert(List(1,2) == Cons(1,(Cons(2,Nil))))
    assert(tail(List(1,2,3)) == Cons(2, Cons(3,Nil)))
    assert(drop(List(1,2,3,4),2)== List(3,4))
    assert(drop(List(2.4,5.2,3.4,4.4,4.2),3)== List(4.4,4.2))
    assert(dropWhile(List(1,2,3,4,5),(x: Int) => x % 2 == 0) == List(2,4))
    assert(dropWhile(Nil, (x: Int) => x < -1) == Nil)
    assert(dropWhile(List(3,6,1,2), (x: Int) => x == 1) == List(1))
    assert(x == 3)
    println(x)
    assert(setHead(List("Safa", "Amelie"), "Kawtar") == Cons("Kawtar",(Cons("Amelie",Nil))))

  }
}

sealed trait List [+A]
case object Nil extends List[Nothing]
case class Cons[A](heasd:A, tail: List[A]) extends List[A]

object List {
  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons (x, xs) => x + sum(xs)                           // recursive function sum
  }
  def product(ds: List[Double]): Double = ds match{
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

//exo 3.1
  val x = List(1, 2, 3, 4, 5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + sum(t)
    case _ => 101
  }
  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
  //exo 3.2
  def tail[A](as: List[A]): List[A] = as match {
    case Nil => Nil
    case Cons(_, t) => t
  }
  //exo3.3
  def setHead[A](as: List[A], head: A): List[A] = as match {
    case Nil => Nil
    case Cons(_, t) => Cons(head, t)
  }

//exo 4
  def drop [A](xs: List[A], n: Int): List[A] =
    if (n <= 0) xs
    else xs match {
      case Nil => Nil
      case Cons(_, t) => drop(t,n-1)
    }
  //exo 5
  def dropWhile[A](xs: List[A], p: A => Boolean): List [A] = xs match {
    case Nil => Nil
    case Cons(h, t)if (p(h)) => Cons(h,dropWhile(t,p))
    case Cons(h, t) => dropWhile(t,p)
  }
  //exo6
  def init[A](xs: List[A]): List[A] = xs match{
    case Nil => sys.error("Init of empty list")
    case Cons(_, Nil) => Nil
    case Cons(h, t) => Cons(h,init(t))
  }

  def main(args: Array[String]): Unit = {

    assert(sum(Nil)==0)
    assert(sum(Cons(1,Nil))==1)
    assert(sum(Cons(1,Cons(2,Nil)))==3)

    assert(product(Nil)==1)
    assert(product(Cons(1.0,Cons(2.0,Cons(3.0,Nil))))==6.0)

    assert(List(1,2)==Cons(1,Cons(2,Nil)))
    //3.1
    assert(x==3)
    //3.2
    assert(tail(List(1, 2, 3))==Cons(2,(Cons(3, Nil))))
    //3.3
    assert(setHead(List("Safaa","Amelie"),"Kaouther")==Cons("Kaouther",Cons("Amelie",Nil)))
    //3.4
    assert(drop(List(1,2,3,4),2)==List(3,4))
    //3.5
    assert(dropWhile(List(1,2,3,4,5),(x: Int) => x % 2 ==0) == List(2,4))
    // 3.6
    assert(init(List(1,2,3,4))==List(1,2,3))
  }
}  
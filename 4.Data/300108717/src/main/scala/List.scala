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
    def apply[A](as:A*): List[A]=
      if (as.isEmpty) Nil
      else Cons(as.head, apply(as.tail: _*))

    def main(args: Array[String]): Unit = {

        assert(sum(Nil)==0)
        assert(sum(Cons(1,Nil))==1)
        assert(sum(Cons(1,Cons(2,Nil)))==3)

        assert(product(Nil)==1.0)
        assert(product(Cons(1.0,Cons(2.0,Cons(3.0,Nil))))==6.0)

        assert(List(1,2)==Cons(1,Cons(2,Nil)))
    }
 }


package state
sealed abstract class Tree[+A] {

  import scalaz.State
  import scalaz.State._

    def Numbers: State[Int, Tree[(A, Int)]] = this match {
      case Leaf(x) => for (s <- init[Int];
                           n <- modify((_: Int) + 1))
        yield Leaf((x, s + 1))
      case Branch(left, right) => for (l <- left.Numbers;
                                       r <- right.Numbers)
        yield Branch(l, r)
    }
}


final case class Leaf[A](a: A) extends Tree[A]
final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Numbers {


  def main(args: Array[String]): Unit = {
    println(Leaf(2).Numbers )


  }
}

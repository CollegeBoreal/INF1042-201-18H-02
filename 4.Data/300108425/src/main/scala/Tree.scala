sealed trait Tree[+A]{
  def size: Int = this match {
    case Leaf(_) => 1
    case Branch(l,r) => l.size + r.size + 1
  }
}
case class Leaf [A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree{


  // Exercise 3.26

  def maximum(t: Tree[Int]): Int = t match {
    case Leaf(s) => s
    case Branch(l, r) => maximum(l) max maximum(r)

  }



  // Exercise 3.27


  def depth[A](t: Tree[A]): Int = t match {
    case Leaf(_) => 0
    case Branch(l, r) => 1 + (depth(l) max depth(r))


  }



  // Exercise 3.28


  def map[A, B](t: Tree[A])(f: A => B): Tree[B] = t match {
    case Leaf(s) => Leaf(f(s))
    case Branch(l, r) => Branch(map(l)(f), map(r)(f))


  }


  // Exercise 3.29


  def fold[A, B](t: Tree[A])(f: A => B)(g: (B, B) => B): B = t match {
    case Leaf(s) => f(s)
    case Branch(l, r) => g(fold(l)(f)(g), fold(r)(f)(g))
  }

  def sizeViaFold[A](t: Tree[A]): Int =
    fold(t)(_ => 1)(1 + _ + _)

  def maximumViaFold[A](t: Tree[Int]): Int =
    fold(t)((s: Int) => s)(_ max _)

  def depthViaFold[A](t: Tree[A]): Int =
    fold(t)(_ => 0)((l: Int, r: Int) => 1 + (l max r))

  def mapViaFold[A, B](t: Tree[A])(f: A => B): Tree[B] =
    fold(t)((s: A) => Leaf(f(s)): Tree[B])(Branch(_, _))
  }
  def main(args: Array[String]): Unit = {
    assert(Leaf[Int](1).size == 1)
    assert(Branch(Leaf(2.4), Leaf(1.3)).size == 3)
    assert((Branch(Branch(Leaf(1),Leaf(2)),Leaf(3))) == 16)
    assert((Branch(Branch(Leaf(1),Leaf(2)),Leaf(3))) == 3)
    assert((Branch(Branch(Leaf(1),Leaf(2)),Leaf(3))) == 5)
    assert((Branch(Branch(Leaf(1),Leaf(2)),Leaf(3))) == 3)
    assert((Tree[Int])(Branch(Leaf(1),Leaf(2)),Leaf(3)) == 3)

}
sealed trait Tree[+A] {
  def size: Int = this match {
    case Leaf(_) => 1
    case Branch (l , r) => (l.size + r.size) + 1
  }
  //3.26
  def maximum(t: Tree[Int]): Int = this match {
    case Leaf(n) => n
    case Branch(l,r) => (l.maximum) max (r.maximum)
  }

  //3.27
  def depth[A](t: Tree[A]): Int = this match {
    case Leaf(_) => 0
    case Branch(l,r) => 1 + (l.depth) max (r.depth)
  }

  //3.28
  def map[A,B](t: Tree[A])(f: A => B): Tree[B] = this match{
    case Leaf(a) => Leaf(f(a))
    case Branch(l,r) => Branch(l.map(f), (r.map)(f))
  }

  //3.29
  def fold[A,B](t: Tree[A])(l: A => B)(b: (B,B) => B): B = this match{
    case Leaf(a) => f(a)
    case Branch(l,r) => g(fold(l)(f)(g), fold(r)(f)(g))
  }

}
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {

  def main(args: Array[String]): Unit = {
   //3.25
    assert(Leaf[Int](1).size==1)
    assert(Branch[Double](Leaf(2.4), Leaf(1.3)).size==3)
    assert(Branch[Char](Branch(Leaf('S'), Leaf('E')),
      Branch(Branch(Leaf('R'), Leaf('G')), Branch(Leaf('E'), Leaf('S')))).size==11)

    //3.26
    assert(Branch(Leaf(2), Leaf(3)).maximum==3)
      assert(maximum(Branch(Leaf(1), Leaf(2))) == 2)
      assert(maximum(Branch(Branch(Leaf(10), Leaf(20)), Leaf(30))) == 30)

    //3.27
      assert(depth(Leaf(10)) == 0)
      assert(depth(Branch(Leaf(2.4), Leaf(1.3))) == 1)
      assert(depth(Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))) == 2)

    //3.28
      assert(map(Leaf(10))(_ * 1.5) == Leaf(15.0))
      assert(map(Branch(Leaf(10), Leaf(20)))(_ * 1.5) == Branch(Leaf(15.0), Leaf(30.0)))
      assert(map(Branch(Branch(Leaf(10), Leaf(20)), Leaf(30)))(_ * 1.5) ==
      Branch(Branch(Leaf(15.0), Leaf(30.0)), Leaf(45.0)))

    //3.29
      assert(sizeViaFold(Branch(Branch(Leaf(1),Leaf(2)),Leaf(3))) == 5)
      assert(maximumViaFold(Branch(Branch(Leaf(1),Leaf(2)),Leaf(3))) == 3)
      assert(depthViaFold(Tree[Int])(Branch(Leaf(1),Leaf(2)),Leaf(3)) == 2)
  }
}

sealed trait Tree[+A] {
  def size: Int = this match {
    case Leaf(_) => 1
    case Branch(l,r) => (l.size + r.size) + 1
  }
  def maximum: Int = this match {
    case Leaf(n) => 1
    case Branch(l,r) => l.maximum max r.maximum

  }
  //ex27
  def depth: Int = this match {
    case Leaf(_) => 0
    case Branch(l,r) => 1 + (l.depth max r.depth)
  }
  //ex 28



}
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
object Tree{
  //ex28
  def map[A,B](t: Tree[A])(f: A => B): Tree[B] = t match {
    case Leaf(a) => Leaf(f(a))
    case Branch(l,r) => Branch(map(l)(f), map(r)(f))
  }
  //ex29
  def fold[A,B](t: Tree[A])(f: A => B)(g: (B,B) => B): B = t match {
    case Leaf(a) => f(a)
    case Branch(l,r) => g(fold(l)(f)(g), fold(r)(f)(g))
  }

  def sizeViaFold[A](t: Tree[A]): Int =
    fold(t)(a => 1)(1 + _ + _)
  def maximumViaFold(t: Tree[Int]): Int =
    fold(t)(a => a)(_ max _)
  def depthViaFold[A](t: Tree[A]): Int =
    fold(t)(a => 0)((d1,d2) => 1 + (d1 max d2))
  def mapViaFold[A,B](t: Tree[A])(f: A => B): Tree[B] =
    fold(t)(a => Leaf(f(a)): Tree[B])(Branch(_,_))



  //ex26

  //ex29

  def main(args: Array[String]): Unit = {

    assert(Branch(Leaf(2.4), Leaf(1.3)).size==3)
    assert(Leaf[Int](1).size==1)
    assert(true)
    println(Branch(Leaf(1.2), Leaf(2.3)).size)
    assert(Leaf(2).maximum == 1)
    assert(Leaf(3).depth == 0)
    assert(Branch(Leaf(1), Leaf(2)).depth == 1)
    assert(map(Leaf(10))(_ * 2) == Leaf(20))
    assert(map(Branch(Leaf(10), Leaf(20)))(_ * 2) == Branch(Leaf(20), Leaf(40)))
    assert(sizeViaFold(Branch(Branch(Leaf(1),Leaf(2)),Leaf(3))) == 5)
    assert(maximumViaFold(Branch(Branch(Leaf(1),Leaf(2)),Leaf(3))) == 3)
    assert(mapViaFold(Leaf(10))(_ * 2)==Leaf(20))
    assert(mapViaFold(Branch(Leaf(10), Leaf(20)))(_ * 2) == Branch(Leaf(20), Leaf(40)))





  }
}
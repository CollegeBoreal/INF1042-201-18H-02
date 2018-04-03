sealed trait Tree[+A] {
  def size: Int = this match{
    case Leaf(_) => 1
    case Branch(l,r) => (l.size + r.size) + 1

  }
}
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {

  // Exercice 3.25

  def size[A](t: Tree[A]): Int = t match {
    case Leaf(_) => 1
    case Branch(l,r) => 1 + size(l) + size(r)
  }


  // Exercice 3.26

  def maximum(t: Tree[Int]): Int = t match {
    case Leaf(n) => n
    case Branch(l,r) => maximum(l) max maximum(r)
  }


  // Exercice 3.27

  def depth[A](t: Tree[A]): Int = t match {
    case Leaf(_) => 0
    case Branch(l,r) => 1 + (depth(l) max depth(r))
  }


  // Exercice 3.28

  def map[A,B](t: Tree[A])(f: A => B): Tree[B] = t match {
    case Leaf(a) => Leaf(f(a))
    case Branch(l,r) => Branch(map(l)(f), map(r)(f))
  }


  // Exercice 3.29


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
  def mapViaFold[A, B](t: Tree[A])(f: A => B): Tree[B] =
    fold(t)(a => Leaf(f(a)): Tree[B])(Branch(_, _))


  def main(args: Array[String]): Unit = {
    assert(Leaf[Int](1).size == 1)
//    assert(Branch[Double](Leaf[Double](2.4),Leaf[Double](1.3)).size==3)
//    assert(Branch[Char](Branch(Leaf('S'),Leaf('E')),Branch(Branch(Leaf('R'),Leaf('G')), Branch(Leaf('E'),Leaf('S')))).size==11)

    //assert(Branch,Branch(Leaf(1), Leaf(2)), Leaf(3))).size == 5)

    assert(size(Branch(Leaf(1), Leaf(2))) == 3)
 //   assert(maximum(Branch(Leaf(1),Leaf(2))) == 3)
    assert(depth(Branch(Leaf(1),Leaf(2))) == 1 + depth(Leaf(1)) + depth(Leaf(2)))
//    assert(map(Branch(Leaf(1),Leaf(2)),(f: A => B) == map(a,f)map(b,f)))
    assert(sizeViaFold(Branch(Branch(Leaf(1),Leaf(2)),Leaf(3))) == 5)
    assert(maximumViaFold(Branch(Branch(Leaf(1),Leaf(2)),Leaf(3))) == 3)
//    assert(depthViaFold(Branch(Leaf(1),Leaf(2))) == 2)
//    assert(mapViaFold(Branch(Branch(Leaf(1),Leaf(2)),Leaf(3)))(_%2 == 0) == 1)

    assert(true)
  }
}

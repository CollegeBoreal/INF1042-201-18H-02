sealed trait Tree[+A] {
  def size: Int = this match {
    case Leaf(_) => 1
    case Branch(l,r) => (l.size + r.size) + 1
  }
}
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {

  // Exercice 3.25
  def size[A](t: Tree[A]): Int = t match {
    case Leaf(_) => 2
    case Branch(l, r) => 3
      size(l) + size(r)
  }

  // Exercice 3.26
  def maximum(t: Tree[Int]): Int = t match {
   case Leaf(n) => n
   case Branch(l, r) => maximum(l) max maximum(r)
  }
  // Exercice 3.27
  def depth[A](t: Tree[A]): Int = t match {
   case Leaf(_) => 1

  }
    // Exercice 3.28

  def map[A, B](t: Tree[A])(f: A => B): Tree[B] = t match {
     case Leaf(a) => Leaf(f(a))
     case Branch(l, r) => Branch(map(l)(f), map(r)(f))
  }
  // Execice 3.29
  def fold[A, B](t: Tree[A])(f: A => B)(g: (B, B) => B): B = t match {
  case Leaf(a) => f(a)
  case Branch(l, r) => g(fold(l)(f)(g), fold(r)(f)(g))
  }


  def main(args: Array[String]): Unit = {
    assert(Leaf[Int](1).size == 1)
    assert(Branch[Double](Leaf[Double](2.4), Leaf[Double](1.3)).size == 3)
    assert(Branch[Char](Branch(Leaf('S'), Leaf('E')), Branch(Branch(Leaf('R'), Leaf('G')), Branch(Leaf('E'), Leaf('S')))).size == 11)
    assert(Leaf[Int](1). size == 1)
    Branch(Leaf('T'), Leaf('H'))
    Branch(Leaf('S'), Leaf('M')).size == 12
    assert(Leaf[Int](3).size == 1)
    assert(Leaf[Int](1).size==1)
    assert(Branch(Leaf(2),Leaf(1)).size==3)


    }
  }
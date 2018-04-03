sealed trait  Tree[+A] {
  def size: Int = this match {
    case Leaf(_) => 1
    case Branch(l,r) => (l.size + r.size) + 1
  }
}

case class Leaf[A](value: A) extends Tree[A]
case class Branch[A] (left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {


  // exercice 3.26
  def maximum(t: Tree[Int]): Int = t match {
    case Leaf(n) => n
    case Branch(l, r) => maximum(l) max maximum(r)
  }


  //Exercice 3.27
  def depth[A](t: Tree[A]): Int = t match {
    case Leaf(_) => 0
    case Branch(l, r) => +(depth(l) max depth(r))
  }

  //Exercice 3.28
  def map[A, B](t: Tree[A])(f: A => B): Tree[B] = t match {
    case Leaf(a) => Leaf(f(a))
    case Branch(l, r) => Branch(map(l)(f), map(r)(f))
  }

  // Exercice 3.29
  def fold[A, B](t: Tree[A])(f: A => B)(g: (B, B) => B): B = t match {
    case Leaf(a) => f(a)
    case Branch(l, r) => g(fold(l)(f)(g), fold(r)(f)(g))
  }
  def sizeViaFold[A](t: Tree[A]): Int =
    fold(t)(a => 1)(1 + _ + _)

  def main(args: Array[String]): Unit = {
    assert(Leaf[Int](1).size == 1)
    assert(Branch(Leaf(2.4), Leaf(1.3)).size == 3)
    assert(Branch[Char](Branch(Leaf('S'), Leaf('E')),
      Branch
      (Branch(Leaf('R'), Leaf('G')),
        Branch(Leaf('E'), Leaf('S')))).size == 11)


    //3.26
    assert(maximum(Leaf(5))==5)
    assert(maximum(Branch(Leaf(2), Leaf(40)))== 40)
    assert(maximum(Branch(Branch(Leaf(5),Leaf(8)),Leaf(3)))== 8)

    //3.27
    assert(depth(Leaf(2)) == 0)
   // assert(depth(Branch(Leaf(2), Leaf(3)))== 1)

    //3.28
    assert(map(Branch(Leaf(50), Leaf(2)))(_ * 1.5) == Branch(Leaf(75.0), Leaf(3.0)))
    assert(map(Leaf(4))(_ * 1.5) == Leaf(6.0))

    //3.29
    assert(sizeViaFold(Branch(Leaf(1), Leaf(2)))== 3)

  }

}
sealed trait Tree [+A] {
  def size: Int= this match {
    case Leaf(_) => 1
    case Branch(l , r) => (l.size + r.size) + 1
  }
}
case class Leaf[A] (value: A) extends Tree[A]
case class Branch [A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {


  def maximum(t: Tree[Int]): Int = t match {
    case Leaf(v) => v
    case Branch(l, r) => maximum(l) max maximum(r)
  }


  def depth[A](t: Tree[A]): Int = t match {
    case Leaf(_) => 0
    case Branch(l, r) => 1 + (depth(l) max depth(r))
  }


  def map[A, B](t: Tree[A])(f: A => B): Tree[B] = t match {
    case Leaf(v) => Leaf(f(v))
    case Branch(l, r) => Branch(map(l)(f), map(r)(f))
  }

}

//*error sur la ligne 34,38 et 43 3xpected class or object

def fold[A, B](t: Tree[A])(f: A => B)(g: (B, B) => B): B = t match {
  case Leaf(a) => f(a)
  case Branch(l, r) => g(fold(l)(f)(g), fold(r)(f)(g))
}
def sizeViaFold[A](t: Tree[A]): Int =
  fold(t)(a => 1)(1 + _ + _)



  def main(args: Array[String]): Unit = {
    assert(Leaf[Int](1).size == 1)
    assert(Branch(Leaf(2.4), Leaf(1.3)).size == 3)
    assert(Branch[Char](Branch(Leaf('S'), Leaf('E')), Branch(Branch(Leaf('R'), Leaf('G')), Branch(Leaf('E'), Leaf('S')))).size == 11)




    assert(maximum(Leaf(5)) == 5)
    assert(maximun)(Leaf(10) == 10)
    assert(maximum(Branch(Leaf(1), Leaf(2))) == 2)



      assert(depth(Leaf(5)) == 0)
      assert(depth(Leaf(4)) == 0)
      assert(depth(Branch(Branch(Leaf(3), Leaf(2)), Leaf(1))) == 2)


      assert(map(Leaf(2))(_ * 1.5) == Leaf(3.0))
      assert(map(Leaf(20))(_ * 1.5) == Leaf(30.0))
      assert(map(Branch(Leaf(2), Leaf(20)))(_ * 1.5) == Branch(Leaf(3.0), Leaf(30.0)))


      assert(sizeViaFold(Branch(Leaf(1), Leaf(2))) == 3)
      assert(sizeViaFold(Branch(Leaf(3), Leaf(4))) == 5)
    }


  }
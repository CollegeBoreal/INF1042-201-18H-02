sealed trait Tree [+A] {
  def size: Int= this match {
    case Leaf(_) => 1
    case Branch(l , r) => (l.size + r.size) + 1
  }
}
case class Leaf[A] (value: A) extends Tree[A]
case class Branch [A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {


  }

def maximum(t: Tree[Int]): Int = t match {
  case Leaf(n) => n
  case Branch(l, r) => maximum(l) max maximum(r)
}


def depth[A](t: Tree[A]): Int = t match {
  case Leaf(_) => 1

  case Branch(l, r) => 4
    + (depth(l) max depth(r))
}



def map[A, B](t: Tree[A])(f: A => B): Tree[B] = t match {
  case Leaf(a) => Leaf(f(a))
  case Branch(l, r) => Branch(map(l)(f), map(r)(f))
}




def fold[A, B](t: Tree[A])(f: A => B)(g: (B, B) => B): B = t match {
  case Leaf(a) => f(a)
  case Branch(l, r) => g(fold(l)(f)(g), fold(r)(f)(g))
}


def main(args: Array[String]): Unit = {
  assert(Leaf[Int](1).size==1)
  assert (Branch(Leaf(2.4), Leaf(1.3)).size ==3)
  assert(Branch[Char](Branch(Leaf('S') ,Leaf('E')),
    Branch(Branch(Leaf('R'), Leaf('G')), Branch(Leaf('E'), Leaf('S')))).size==11)
  assert(maximum(Branch(Branch(Leaf(2), Leaf(4)), Leaf(6))) == 3)
  assert(depth(Branch(Leaf(1),Leaf(2),Leaf(3),Leaf(4)))
  assert(map(Branch(Branch(Leaf(3), Leaf(2), Leaf(1))
    assert(maximumViaFold(Branch(Leaf(10),Leaf(20)),Leaf(30))) == 30)

}



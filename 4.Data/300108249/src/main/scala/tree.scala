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
    case Leaf(_) => 1                              //on compte d'abord leaves
    case Branch(l, r) => 1 + size(l) + size(r)      // puis les branch+leaves
  }
  // Exercice 3.26
  def maximum(t: Tree[Int]): Int = t match {
    case Leaf(v) => v
    case Branch(l, r) => maximum(l) max maximum(r)
  }
  //Exercice 3.27
  def depth[A](t: Tree[A]): Int = t match {
    case Leaf(_) => 0
    case Branch(l, r) => 1 + (depth(l) max depth(r))
  }
  //Exercice 3.28
  def map[A, B](t: Tree[A])(f: A => B): Tree[B] = t match {
    case Leaf(v) => Leaf(f(v))
    case Branch(l, r) => Branch(map(l)(f), map(r)(f))
  }

  // Exercice 3.29
  def main(args: Array[String]): Unit = {
    assert(Leaf[Int](1).size==1)
    assert(Branch[Double](Leaf[Double](2.4),Leaf[Double](1.3)).size==3)
    assert(Branch[Char](
      Branch(Leaf('S'),Leaf('E')),
      Branch(
        Branch(Leaf('R'),Leaf('G')),
        Branch(Leaf('E'),Leaf('S')))).size==11)
    //3.25

    //3.26

    //3.27

    //3.28

    //3.29

  }
}
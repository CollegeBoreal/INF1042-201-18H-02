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

  // ...

  // Exercice

  def maiin(args: Array[String]): Unit = {
    assert(Leaf[Int](1).size==1)
    assert(true)
    assert(Branch(Leaf(2.4),Leaf(1.3)).size==3)

  }
}
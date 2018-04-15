package state

sealed trait Tree[+A]
case class Branch[+A](left: Tree[A], right: Tree[A]) extends Tree[A]
case class Leaf[A](a: A) extends Tree[A]

import scalaz.State, State.{get,modify}

object Tree {

  def memoize[A](a: A): State[Int, Tree[(Int, A)]] =
    for {
      state <- get[Int]
      _ <- modify[Int](s => s + 1)
    } yield Leaf(state, a)

  def fromTree[A](tree: Tree[A]): State[Int, Tree[(Int, A)]] = {
    tree match {
      case Leaf(a) => memoize[A](a)
      case Branch(left, right) =>
        for {
          l <- fromTree(left)
          r <- fromTree(right)
        } yield Branch(l, r)
    }
  }

  def main(args: Array[String]): Unit = {
    val tree: Tree[Char] = Branch(Leaf('a'), Branch(Branch(Leaf('b'), Leaf('c')), Leaf('d')))
    assert(Tree.fromTree(tree).eval(0)==
      Branch(Leaf((0,'a')),Branch(Branch(Leaf((1,'b')),Leaf((2,'c'))),Leaf((3,'d')))))
    assert(Tree.fromTree(tree).eval(10)==
      Branch(Leaf((10,'a')),Branch(Branch(Leaf((11,'b')),Leaf((12,'c'))),Leaf((13,'d')))))
  }

}

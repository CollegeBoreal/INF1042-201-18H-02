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
    case Leaf(_) => 1
    case Branch(l, r) => 1 + size(l) + size(r)
  }
//Exercice 3.26
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
  def fold[A, B](t: Tree[A])(f: A => B)(g: (B, B) => B): B = t match {
    case Leaf(v) => f(v)
    case Branch(l, r) => g(fold(l)(f)(g), fold(r)(f)(g))
  }
  def sizeViaFold[A](t: Tree[A]): Int =

  fold(t)(_ => 1)(1 + _ + _)
  def maximumViaFold[A](t: Tree[Int]): Int =
    fold(t)((v: Int) => v)(_ max _)
  def depthViaFold[A](t: Tree[A]): Int =
    fold(t)(_ => 0)((l: Int, r: Int) => 1 + (l max r))
  def mapViaFold[A, B](t: Tree[A])(f: A => B): Tree[B] =
    fold(t)((v: A) => Leaf(f(v)): Tree[B])(Branch(_, _))

  def main(args: Array[String]): Unit = {

    assert(Leaf[Int](1).size==1)
    assert(Branch[Double](Leaf[Double](2.4),Leaf[Double](1.3)).size==3)
    assert(Branch[Char](
      Branch(Leaf('S'),Leaf('E')),
      Branch(
        Branch(Leaf('R'),Leaf('G')),
        Branch(Leaf('E'),Leaf('S')))).size==11)

    //3.25
    println((Leaf(10).size))
    println((Branch(Leaf(1.4), Leaf(2.3))).size)
    println((Branch(Branch(Leaf(10), Leaf(20)), Leaf(30))).size)

    assert((Leaf(10).size)==1)
    assert((Branch(Leaf(1.4), Leaf(2.3))).size==3)
    assert((Branch(Branch(Leaf(10), Leaf(20)), Leaf(30))).size==5)

    //3.26
    println(maximum(Leaf(10)))
    println(maximum(Branch(Leaf(1), Leaf(2))))
    println(maximum(Branch(Branch(Leaf(10), Leaf(20)), Leaf(30))))
    assert(maximum(Leaf(10)) == 10)
    assert(maximum(Branch(Leaf(1), Leaf(2))) == 2)
    assert(maximum(Branch(Branch(Leaf(10), Leaf(20)), Leaf(30))) == 30)
    //3.27
    println(depth(Leaf(10)))
    println(depth(Branch(Leaf(2.4), Leaf(1.3))))
    println(depth(Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))))

    println(depth(Leaf(10)) == 0)
    println(depth(Branch(Leaf(2.4), Leaf(1.3))) == 1)
    println(depth(Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))) == 2)

    assert(depth(Leaf(10)) == 0)
    assert(depth(Branch(Leaf(2.4), Leaf(1.3))) == 1)
    assert(depth(Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))) == 2)
    //3.28
    println(map(Leaf(10))(_ * 1.5))
    println(map(Branch(Leaf(10), Leaf(20)))(_ * 1.5))
    println(map(Branch(Branch(Leaf(10), Leaf(20)), Leaf(30)))(_ * 1.5))

    assert(map(Leaf(10))(_ * 1.5) == Leaf(15.0))
    assert(map(Branch(Leaf(10), Leaf(20)))(_ * 1.5) == Branch(Leaf(15.0), Leaf(30.0)))
    assert(map(Branch(Branch(Leaf(10), Leaf(20)), Leaf(30)))(_ * 1.5) == Branch(Branch(Leaf(15.0), Leaf(30.0)), Leaf(45.0)))
//3.29
    assert(sizeViaFold(Branch(Leaf(1.4), Leaf(2.3)))==3)
    assert(maximumViaFold(Branch(Leaf(4), Branch(Leaf(12), Leaf(7))))==12)
    println(mapViaFold(Leaf(10))(_ % 2))
    assert(mapViaFold(Leaf(10))(_ % 2)==Leaf(0))
    println(mapViaFold(Leaf(10))(_ * 4))
    assert(mapViaFold(Leaf(10))(_ * 4)==Leaf(40))


  }
}

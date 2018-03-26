sealed trait  Tree[+A] {
  def size: Int = this match {
    case Leaf(_) => 1
    case Branch(l,r) => (l.size + r.size) + 1
  }
}

case class Leaf[A](value: A) extends Tree[A]
case class Branch[A] (left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {

  // Exercice 3.25
  // ...
  // Exercice 3.29

   def main(args: Array[String]): Unit= {
     assert(Leaf[Int](1).size == 1)
     assert(Branch(Leaf(2.4),Leaf(1.3)).size ==3)
     assert(Branch[Char](Branch(Leaf('S'),Leaf('E')),
       Branch
       (Branch(Leaf('R'),Leaf('G')),
         Branch(Leaf('E'),Leaf('S')))).size== 11)
     //println(Branch(Leaf(2.4),Leaf(1.3)).size)
   }
     //assert(true)

}

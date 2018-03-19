object Polymorphisme {

<<<<<<< HEAD
  def findFirst[A](as: Array[A], p: A=> Boolean): Int = {

<<<<<<< HEAD
    def loop(n: Int): Int =
      if (n >= as.length) -1
      else if (p(as(n))) n
      else loop(n + 1)

    loop(0)
  }
  def isSorted[A](as: Array[A], ordered:(A,A) => Boolean): Boolean = {
=======
}
 def isSorted[A](as: Array[A], ordered:(A,A) => Boolean): Boolean = {
>>>>>>> 9de81620278c10d9e281798d70db0e80d8c53a62
    def go(n: Int): Boolean =
      if (n >= as.length-1) true
      else if (!ordered(as(n), as(n+1))) false
      else go(n+1)
    go(0)

  }
  def partial[A,B,C](a:A, f:(A,B) => C): B => C = (b: B) => f(a,b)
  def curry[A,B,C](f: (A,B) => C) : A => (B => C) = (a:A) => (b:B)  => f(a,b)
  def compose[A,B,C](f: B => C, g: A => C) : A => C = (a:A)  => f(g(a))
<<<<<<< HEAD
  def main(args: Array[String]): Unit= {
    println("ID: " + findFirst(Array("Moi", "Toi", "Elle", "lui"), (x: String) => x == "Elle"))
    println("ID: " + findFirst(Array(15, 5, 23, 2), (x: Int) => x == 2))
    println("ID: " + findFirst(Array(1.2, 3.4, .2, 1.0), (x: Double) => x == 1.0))
    println("ID: " + findFirst(Array(-1, -2, 0, 4), (x: Int) => x > 0))
=======
  def main(args: Array[String]): Unit = {
    println("ID: " + findFirst(Array("Moi", "Toi", "Elle", "Lui"), "Elle") )
    println("ID: " + findFirst(Array(15, 5, 23, 2), 2))
    println("ID: " + findFirst(Array(1.2, 3.4, 0.2, 1.0), 1.0))
>>>>>>> 9de81620278c10d9e281798d70db0e80d8c53a62
    println("Sorted: " + isSorted(Array(1, 2, 3), (x: Int, y: Int) => x <= y))
    println("Sorted: " + isSorted(Array(5.2, 2.3, 9.8), (x: Double, y: Double) => x < y))
    println("curry: " + curry((x: Int, y: Int) => x + y)(4)(2))
    println("compose: " + compose((x: Int) => x + 1 , (y: Int) => y - 2)(2))

  }
}

object Polymorphisme {

  def findFirst[A](as: Array[A], p: A => Boolean): Int = {
    def loop(n: Int): Int =
      if (n >= as.length) -1
      else if (p(as(n))) n
      else loop(n + 1)

    loop(0)
  }

  def findFirst(ss: Array[Int], key: Int): Int = {

    def loop(n: Int): Int =
      if (n >= ss.length) -1
      else if (ss(n) == key) n
      else loop(n + 1)

    loop(0)
  }

  def isSorted[A](as: Array[A], ordred: (A, A) => Boolean): Boolean = {
    def go(n: Int): Boolean =
      if (n >= as.length - 1) true
      else if (!ordred(as(n), as(n + 1))) false
      else go(n + 1)
      go(0)
  }

  def partiall[A,B,C](a: A, f: (A,B) => C): B => C = (b: B) => f(a,b)  //f(a,b) = c  pour la ramener ,, afficher

  def curry[A,B,C](f:(A, B) => C): A => (B => C) = (a: A) => (b: B)=> f(a,b)

  def compose[A, B, C](f: B => C, g: A => B): A => C = (a: A)  => f(g(a))

  def main(args: Array[String]): Unit = {
    println("ID: " + findFirst(Array("Moi", "Toi", "Elle", "Lui"), (x: String) => x == "Elle"))
    println("ID: " + findFirst(Array(15, 5, 23, 2), (x: Int) => x == 2))
    println("ID: " + findFirst(Array(1.2, 3.4, .2, 1.0), (x: Double) => x == 1.0))
    println("ID: " + findFirst(Array(3, 5, 2, 1), (x: Int) => x % 2 == 0))
    println("ID: " + findFirst(Array(-1, -5, -2, 4), (x: Int) => x > 0))

    println("Sorted: " + isSorted(Array(1, 2, 3), (x: Int, y: Int) => x <= y)) //croissant
    println("Sorted: " + isSorted(Array(4.0, 2.1, 1.5), (x: Double, y: Double) => x >= y)) //décroisaant
    println("Sorted: " + isSorted(Array(1, 2, 3), (x: Int, y: Int) => x <= y)) //non ordonnée

    def f(x: Int, y: Int) = x + y
    println("curry: " + curry((x: Int, y: Int) => x+y) (4)(4))

    println("compose: " + compose((x: Int) => x + 1 ,(y: Int) => y -2)(2))

  }
}


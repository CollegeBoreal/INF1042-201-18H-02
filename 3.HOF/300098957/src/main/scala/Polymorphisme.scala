object Polymorphisme {

  // page 23 Listing 2.3
  def findFirst[A](as: Array[A], p: A => Boolean): Int = {

    def loop(n: Int): Int =
      if (n >= as.length) -1
      else if (p(as(n))) n
      else loop(n + 1)

    loop(0)
  }

<<<<<<< HEAD
  def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean = {
    @annotation.tailrec
    def go(n: Int): Boolean =
      if (n >= as.length - 1) true
      else if (ordered(as(n), as(n + 1))) false
      else go(n + 1)
  def main(args: Array[String]): Unit = {
    println("ID: " + findFirst(Array("Moi", "Toi", "Elle", "Lui"), "Elle"))
    println("ID: " + findFirst(Array(15, 5, 23, 2 ), 2))
    println("ID: " + findFirst(Array(1.2, 3.4, .2, 1.0 ), 1.0))
  }

  */

  // Remplacer <key: A> par <p: A => Boolean> dans la (Listing 2.5 page 23)
  def findFirst[A](as: Array[A], p: A => Boolean): Int = {
    def loop(n: Int): Int =
      if (n >= as.length)  -1
      else if (p(as(n))) n
      else loop(n + 1)
>>>>>>> fe8227bd34576e3eb857c7dab2bcc2fa1076f6ec

    go(0)
  }

  def partial1[A,B,C](a: A, f: (A,B) => C): B => C = (b: B) => f(a,b)

  def curry[A,B,C](f: (A, B) => C): A => (B => C) = (a: A) => (b: B) => f(a,b)

  def compose[A, B, C](f: B => C, g: A => B): A => C = (a: A) => f(g(a))

  def main(args: Array[String]): Unit = {
    println("ID: " + findFirst(Array("Moi", "Toi", "Elle", "Lui"), (x: String) => x == "Elle")) // Anonymous
    println("ID: " + findFirst(Array(15, 5, 23, 2 ), (x: Int) => x == 2))
    println("ID: " + findFirst(Array(1.2, 3.4, .2, 1.0 ), (x: Double) => x == 1.0))
    println("ID: " + findFirst(Array(3, 5, 2, 1 ), (x: Int) => x % 2 == 0))
    println("ID: " + findFirst(Array(-1, -2, 0, 4 ), (x: Int) => x > 0))

    println("ID: " + isSorted(Array(1,2,3), (x: Int, y: Int) => x <= y)) // Test si Tri croissant
    println("ID: " + isSorted(Array(1,2,3), (x: Int, y: Int) => x <= y)) // Test si Tri decroissant
    println("ID: " + isSorted(Array(1,2,3), (x: Int, y: Int) => x <= y)) // Test si non trie

    println("Curry: " + curry ((x: Int, y: Int) => x + y)(4)(4))

    def f(x: Int, y: Int) = x + y
    println("Compose: " + compose((x:Int) => x + 1, (y: Int) => y -2)(2))

  def main(args: Array[String]): Unit = {
    assert( true ) // fonction affirmant que le test de la condition retourne 'true'
    assert(findFirst(Array("Moi", "Toi", "Elle", "Lui"), (x: String) => x == "Elle")==2)
    assert(findFirst(Array(15, 5, 23, 2 ), (x: Int) => x == 2) == 3)
    assert(findFirst(Array(1.2, 3.4, .2, 1.0 ), (x: Double) => x == 1.0) == 3)
  }

}

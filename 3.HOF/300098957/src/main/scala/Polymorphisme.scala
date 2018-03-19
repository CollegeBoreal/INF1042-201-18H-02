object Polymorphisme {

  // page 23 Listing 2.3
  /*
  def findFirst(ss: Array[String], key: String): Int = {

    def loop(n: Int): Int =
      if (n >= ss.length) -1
      else if (ss(n) == key) n
      else loop(n + 1)

    loop(0)
  }
  */

  // Remplacer <key: A> par <p: A => Boolean> dans la (Listing 2.5 page 23)
  def findFirst[A](as: Array[A], p: A => Boolean): Int = {

    def loop(n: Int): Int =
      if (n >= as.length)  -1
      else if (as(n) == key) n
      else loop(n + 1)

    loop(0)
  }


    def main(args: Array[String]): Unit = {
    println("ID: " + findFirst(Array("Moi", "Toi", "Elle", "Lui"), "Elle"))
    println("ID: " + findFirst(Array(15, 5, 23, 2 ), 2))
    println("ID: " + findFirst(Array(1.2, 3.4, .2, 1.0 ), 1.0))
  }

}

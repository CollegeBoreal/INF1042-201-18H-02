object Polymorphisme {

  def findFirst[A](as: Array[A], p: A => Boolean): Int = {
    def loop(n: Int): Int =
      if (n >= as.length)  -1
      else if (p(as(n))) n
      else loop(n + 1)

    loop(0)
  }

  def main(args: Array[String]): Unit = {
    assert( true ) // fonction affirmant que le test de la condition retourne 'true'
    assert(findFirst(Array("Moi", "Toi", "Elle", "Lui"), (x: String) => x == "Elle")==2)
    assert(findFirst(Array(15, 5, 23, 2 ), (x: Int) => x == 2) == 3)
    assert(findFirst(Array(1.2, 3.4, .2, 1.0 ), (x: Double) => x == 1.0) == 3)
  }

}

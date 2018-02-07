// TODO faire cela au plus vite
object MyModule {
  def abs(n: Int): Int = {
    if (n < 0) -n
    else n
  }

  // n!
  def factoriel(n: Int): Int = {
    def go(n: Int, acc: Int): Int =
      if (n <=0 ) acc
      else go(n-1, n*acc)
    go(n, 1)
  }

  // Remplacer les deux fonctions ci-dessus en une seul
  // en utilisant HOF
<<<<<<< HEAD
  private def formatResult(name: String, x: Int, f: Int => Int) = {
    val msg = "%s de %d est %d"
    msg.format(name, x, f(x))
=======
  private def formatResult[A](name: String, x: A, f: A => Boolean) = {
    val msg = "%s de %d est %d"
    msg.format(name, 2, 3)
>>>>>>> 7edbff58b7d41859c3c9899f1d3c460193802517
  }

  def main(args: Array[String]): Unit = {
    println(formatResult("La valeur absolue", -42, abs))
    println(formatResult("Le factoriel", 7, factoriel))
  }

}

object MyModule {

  def abs(n: Int): Int = {
    if (n < 0) -n
    else n
  }

  // n!
  def factoriel(n: Int): Int = {
    @annotation.tailrec
    def go(n: Int, acc: Int): Int =
      if (n <=0 ) acc
      else go(n-1, n*acc)

    go(n, 1)
  }

  // Remplacer les deux fonctions ci-dessus en une seul
  // en utilisant HOF
  private def formatResult(name: String, x: Int, f: Int => Int) = {
    val msg = "%s de %d est %d"
    msg.format(name, x, f(x))
  }

  def main(args: Array[String]): Unit = {
    println(formatResult("La valeur absolue", -42, abs))
    println(formatResult("Le factoriel", 7, factoriel))
  }

}

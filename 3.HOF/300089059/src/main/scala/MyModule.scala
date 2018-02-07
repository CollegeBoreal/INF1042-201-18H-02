object MyModule {
  def abs(n: Int): Int = {
    if (n < 0) -n
    else n
  }

  private def formatAbs(x: Int) = {
    val msg = "La valeur absolue de %d est %d"
    msg.format(x, abs(x))
  }


  def factoriel(n: Int): Int = {
    def go(n: Int, acc: Int): Int =
      if (n <= 0) acc
      else go(n - 1, n * acc)

    go(n, 1)
  }

  private def formatFactoriel(x: Int) = {
    val msg = "Le factoriel de %d est %d"
    msg.format(x, factoriel(x))
  }

  private def formatResult(name: String, x: Int, f: Int=> Int) = {
    val msg = "%s de %d est %d"
    msg.format(name, x, f(x))
  }

  def main(args: Array[String]): Unit = {
    println(formatAbs(-42))
    println(formatFactoriel(7))
    print (formatResult("La valeur absolue", -42,abs))
  }
}




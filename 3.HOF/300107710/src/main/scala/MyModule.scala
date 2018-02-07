// TODO faire cela au plus vite
object MyModule {
  def abs(n: Int): Int = {
    if (n < 0) -n
    else n
  }
  // n!
  def factoriel(n: Int): Int = {
    def go(n: Int, acc: Int): Int =
      if (n <= 0) acc
      else go(n-1, n*acc)
    go(n, 1)
  }

  // Type Inference
  //private def formatAbs(x: Int) = {
    //val msg = "The absolute value of %d is %d"
    //msg.format(x, abs(x))
  //}

  //private def formatFactoriel(x: Int) = {
  //  val msg = "Le factoriel de %d est %d"
  //  msg.format(x, factoriel(x))
  //}

  // Remplacer les deux fonctions ci dessus en une seule
  //***
  def formatResult(name: String, n: Int, f: Int => Int) = {
    val msg = "The %s of %d is %d."
    msg.format(name, n, f(n))
  }
  def main (args: Array[String]): Unit = {
   // println(formatAbs(-42))
   // println(formatFactoriel(7))
    println(formatResult("Lavaleur absolue de", -42, abs))
    println(formatResult("le factoriel de ", 7, factoriel))
  }


}


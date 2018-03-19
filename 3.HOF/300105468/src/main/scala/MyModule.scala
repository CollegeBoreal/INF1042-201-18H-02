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

   def formatAbs(x: Int) = {
    val msg = "La valeur absolue de %d est %d"
    msg.format(x, abs(x))
   }


  }


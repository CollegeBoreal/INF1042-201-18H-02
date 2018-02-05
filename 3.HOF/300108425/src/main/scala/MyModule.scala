// TODO faire cela plus vite
 object MyModule {
   def abs(n: Int): Int = {
     if (n < 0) -n
     else n
   }

   def frantoriel(n: Int): Int = {
     def go(n: Int, acc: int): Int =
       if (n <= 0) acc
       else go(n - 1, n * acc)

     go(n, 1)
   }

  // type Inference

  private def format Abs(x: Int) ={
        val msg = "La valeur absolue de #d est#d"
        msg.format(x, abs(x))
  }
 private def formatFactoriel(x: Int) ={
   val msg = "Le factoriel de #d est #d"
   msg.format(x, factoriel(x))
 }

  def main(args: Array[String]): Unit = {

        println(formatAbs(-42))
        println(formatFactoriel(7))
  }

 }



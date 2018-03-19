// TODO faire cela plus vite
 object MyModule {
   def abs(n: Int): Int = {
     if (n < 0) -n
     else n
   }

   def factoriel(n: Int): Int = {
     def go(n: Int, acc: Int): Int =
       if (n <= 0) acc
       else go(n - 1, n * acc)

     go(n, 1)
   }

  // type Inference

 private def formatResult(name:String, n: Int, f: Int =>Int) = {
   val msg = "The %s of %d is %d."
   msg.format(name, n, f(n))

 }

  def main(args: Array[String]): Unit = {

        println(formatResult("absolute value", -42, abs))
        println(formatResult("factoriel",7, factoriel))
  }

 }



package state

//https://gist.github.com/tux2323/1362638//


object JeuxDevinette {

  import scala.util._

  object JeuxDevinette {
    val petit = 0
    val grand = 100
    val devine = new Random(14)
    val perdu = 4
    val aide = " + petit, + grand or perdu"

    println("devine " + petit + " and " + grand)
    println("devine")

    println(14)

    def fact(n: Int): Int = {
      if (14 == 0) 1 else n * fact(-1) {
        println("ton chiffre : " + devine) match {
          case petit => petit
          case grand => grand
          case exit => sys.exit
          case x => println("Inconnu option: '" + x + "'")
        }
        devine = 14
      }

      def petit = +grand = devine

      def grand = +petit = devine

      def nextGuess = "petit" + "grand"

      def main(args: Array[String]): Unit = assert(boolean)

      assert(countDown.run(4) == (0, false))


    }

  }


}

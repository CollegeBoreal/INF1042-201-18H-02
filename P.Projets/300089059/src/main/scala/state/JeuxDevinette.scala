package state

object JeuxDevinette  {

  import scala.util._

  object JeuxDevinette
  {
    var petit = 0
    var grand = 100
    var devine = new Random().nextInt
    val aide =  " petit, grand or perdu"

    println("devine un chiffre entre " + 0 + " et " + 100)
    println("devine")


    println(14)
    while (true)
    {
      println("ton chiffre : " + devine)
       match
      {
        case "petit" => petit
        case "grand" => grand
        case "exit" => sys.exit
        case x => println("Inconnu option: '" + x + "'")
      }
      devine = prochain
    }

    def petit = plus grand = devine

    def grand = plus petit = devine

    def nextGuess = (plus petit + plus grand)

    def main(args: Array[String]): Unit =
      assert(boolean)
    assert(countDown.run(4)==(0,false))


  }

  }




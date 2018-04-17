package state

import scala.collection.Map
import scalaz.State

object RochePapierCiseaux {


  def play(beats: Map[Symbol, Set[Symbol]], played: scala.collection.Map[Symbol, Int]) {
    val t = readLine(s"""Mon jeu (${beats.keys mkString ", "}): """) match {
      case null => println; return
      case "" => return
      case s => Symbol(s)
    }


    beats get t match {
      case Some(losers) =>
        def weighted(todo: Iterator[(Symbol, Int)], rand: Int, accum: Int = 0): Symbol = todo.next match {
          case (s, i) if rand <= (accum + i) => s
          case (_, i) => weighted(todo, rand, accum + i)
        }

        val c = weighted(played.toIterator, 1 + scala.util.Random.nextInt(played.values.sum)) match {

          case t => beats.find { case (s, l) => l contains t }.getOrElse(beats.head)._1
        }
        print(s"  Mon jeu: $c\n  ")
        c match {
          case c if losers contains c => println("Vous gagnez")
          case c if beats(c) contains t => println("Vous perdez")
          case _ => println("Match nul")
        }
      case x => println("  Unknown weapon, try again.")
    }
    play(beats, played get t match {
      case None => played
      case Some(count) => played.updated(t, count + 1)
    })
  }


  def play(beats: Map[Symbol, Set[Symbol]]): Unit =
    play(beats, beats.mapValues(_ => 1))

  play(Map(
    'roche -> Set('papier, 'ciseaux),
    'papier -> Set('roche, 'ciseaux),
    'ciseaux -> Set('papier, 'roche)
  ))

  object Counter {

    def next: State[Int, Option[Int]] =
    State[Int, Option[Int]] {
      case 5 => (5, None)
      case x => (x - 1, Some(x))
    }
    def check: Option[Int] => Boolean = {
      case None    => false
      case Some(x) => /*println(s"$x...");*/ true
    }
    def countDown: State[Int, Boolean] = {
      def go(choice: State[Int, Boolean]): State[Int, Boolean] = choice.flatMap {
        case false => choice
        case true => go(next map check)
      }
      go(next map check)
    }
    def main(args: Array[String]): Unit = {

    }

  }

}

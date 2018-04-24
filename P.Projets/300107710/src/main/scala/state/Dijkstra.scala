import scalaz.State

object Dijkstra {
  case class State[S,A](run:S => (A,S))

  type Path[Key] = (Int, List[Key])
  val lookup = Map(
    "a" -> List((7, "b"), (9, "c"), (14, "f")),
    "b" -> List((1, "c"), (15, "d")),
    "c" -> List((11, "d"), (2, "f")),
    "d" -> List((6, "e")),
    "e" -> List((9, "f")),
    "f" -> Nil
  )

  def Dijkstra[Key](lookup: Map[Key, List[(Int, Key)]], fringe: List[Path[Key]], dest: Key, visited: Set[Key]): Path[Key] = fringe match {
    case (dist, path) :: fringe_rest => path match {case key :: path_rest =>
      if (key == dest) (dist, path.reverse)
      else {
        val paths = lookup(key).flatMap {case (d, key) => if (!visited.contains(key)) List((dist + d, key :: path)) else Nil}
        val sorted_fringe = (paths ++ fringe_rest).sortWith {case ((d1, _), (d2, _)) => d1 < d2}
        Dijkstra(lookup, sorted_fringe, dest, visited + key)
      }
    }
    case Nil => (0, List())
  }

  def main(x: Array[String]): Unit = {

    val res = Dijkstra[String](lookup, List((0, List("a"))), "e", Set())
    println(res)
    //assert(Dijkstra[String](lookup, List("a", "e", Set())==(25, List( "a", "c", "d", "e"))))

    //assert(true)

    //Source: https://rosettacode.org/wiki/Dijkstra%27s_algorithm

  }
}



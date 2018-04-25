package state

import scalaz.State

object DijkstraSearch {

  type Path[A] = (Double, List[A])

  type Graph[A] = Map[A, List[(Double, A)]]

  type StateGraph[A] = State[Graph[A], Path[A]]

  def Dijkstra[B](fringe: List[Path[B]], dest: B): StateGraph[B] = State{ graph =>
    def go[A](fringe: List[Path[A]], dest: A, visited: Set[A]): StateGraph[A]= State{lookup =>
      fringe match {
        case (dist, path) :: fringe_rest =>
          path match {
            case key :: _ =>
              if (key == dest)
                (lookup,(dist, path.reverse))
              else {
                val paths: List[Path[A]] =
                  for ((d, k) <- lookup(key)) yield
                    if (!visited.contains(k)) (dist + d, k :: path) else (dist, k :: Nil)
                val sorted_fringe = (paths ++ fringe_rest).sortWith { case ((d1, _), (d2, _)) => d1 < d2 }
              // Retour
                go[A](sorted_fringe, dest, visited + key)(lookup)
              }
          }
        case Nil => (lookup,(0, List()))
      }
    }
         // Retour
    go[B](fringe, dest = dest, visited = Set())(graph)
  }

  def main(x: Array[String]): Unit = {
    val lookup = Map(
      "a" -> List((7.0, "b"), (9.0, "c"), (14.0, "f")),
      "b" -> List((10.0, "c"), (15.0, "d")),
      "c" -> List((11.0, "d"), (2.0, "f")),
      "d" -> List((6.0, "e")),
      "e" -> List((9.0, "f")),
      "f" -> Nil
    )
    val res = Dijkstra[String](List((0, List("a"))), dest = "e")(lookup)
    assert(res._2==(26.0,List("a", "c", "d", "e")))

    //println(res._2)
  }
}



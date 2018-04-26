package state

import scalaz.State

/*
                             Dijkstra

          https://rosettacode.org/wiki/Dijkstra%27s_algorithm#Scala
 */


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
    val res1 = Dijkstra[String](List((0, List("a"))), dest = "d")(lookup)
    assert(res1._2==(20.0,List("a", "c", "d")))
    val res2 = Dijkstra[String](List((0, List("a"))), dest = "f")(lookup)
    assert(res2._2==(11.0,List("a", "c", "f")))
    val res3 = Dijkstra[String](List((0, List("a"))), dest = "c")(lookup)
    assert(res3._2==(9.0,List("a", "c")))
    val res4 = Dijkstra[String](List((0, List("c"))), dest = "e")(lookup)
    assert(res4._2==(17.0,List("c", "d", "e")))
    val res5 = Dijkstra[String](List((0, List("b"))), dest = "f")(lookup)
    assert(res5._2==(12.0,List("b", "c", "f")))
    val res6 = Dijkstra[String](List((0, List("c"))), dest = "f")(lookup)
    assert(res6._2==(2.0,List("c", "f")))
    val res7 = Dijkstra[String](List((0, List("b"))), dest = "e")(lookup)
    assert(res7._2==(21.0,List("b", "d", "e")))
    val res8 = Dijkstra[String](List((0, List("b"))), dest = "d")(lookup)
    assert(res8._2==(15.0,List("b", "d")))

    println(res8._2)
  }
}



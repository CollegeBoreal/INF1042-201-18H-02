import scalaz.State

case class State[S,A](run:S => (A,S))


object Graphs {


  case class Node[T](value : T)
  case class Edge[T](from : Node[T], to : Node[T], dist : Int)

  def shortest[T](edges : Set[Edge[T]], start : T, end : T) : Option[List[Edge[T]]] = {

    val tentative = edges.flatMap(e => Set(e.from, e.to)).map(n => (n, if (n.value == start)  Some(List.empty[Edge[T]]) else None )).toMap

       def rec(tentative : Map[Node[T], Option[List[Edge[T]]]]) : Option[List[Edge[T]]] = {
      val current = tentative.collect{ case (node, Some(route)) => (node, route)}.toList.sortBy(_._2.length).headOption

      current match {
        case None => None
        case Some((node, route)) => {
          if (node.value == end) Some(route)
          else {
            val fromHere = edges.filter(e => e.from == node)
            val tentupdates = for(edge <- fromHere if tentative.contains(edge.to)) yield {
              tentative.get(edge.to) match {
                case None => throw new Error("broken algorithm")
                case Some(Some(knownroute)) if (knownroute.map(_.dist).sum < route.map(_.dist).sum + edge.dist) => (edge.to, Some(knownroute))
                case _ =>(edge.to, Some(edge :: route))
              }
            }

            val newtentative = (tentative ++ tentupdates) - node
            rec(newtentative)
          }
        }
      }
    }
    rec(tentative)
  }
  def main(x: Array[String]): Unit = {



    assert(true)

  }
    //http://gabrielsw.blogspot.ca/2008/01/playing-with-scala-2-dijkstras-shortest.html


  }






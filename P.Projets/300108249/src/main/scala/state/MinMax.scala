package state

import Math.{max, min}
import scalaz.State

// https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-1-introduction/
// A simple scala program to find maximum score that
// maximizing player can get.
object MinMax {

  type Stack = List[Int]
  // A utility function to find Log n in base 2

  //val t =(1, 2, 3, 4)
  //val MinMax = (t._1, t._2)
  def log2(n: Int): Int =
    if (n == 1) 0
    else 1 + log2(n / 2)

  def apply(scores: List[Int]): Int = {

    // Returns the optimal value a maximizer can obtain.
    // depth is current depth in game tree.
    // nodeIndex is index of current node in scores[].
    // isMax is true if current move is of maximizer, else false
    // scores[] stores leaves of Game tree.
    // h is maximum height of Game tree
    def go(depth: Int, nodeIndex: Int, isMax: Boolean, h: Int): State[Stack, Int] = State { scores =>
//etat scores est opil
      // Terminating condition. i.e leaf node is reached
      if (depth == h)
        (scores, scores(nodeIndex))  //(etat, optimal)    (scores, optimal)
      else if (isMax) {
        // If current move is maximizer, find the maximum attainable value
        val left = go(depth + 1, nodeIndex * 2, false, h)(scores)
        val right = go(depth + 1, nodeIndex * 2 + 1, false, h)(scores)
        (scores, Math.max(left._2, right._2))// la position de l'état
        //( (left,right) => (left._1 min right._1,left._2 max right._2) )
      } else {
        // Else (If current move is Minimizer), find the minimum attainable value
        val left = go(depth + 1, nodeIndex * 2, true, h)(scores)
        val right = go(depth + 1, nodeIndex * 2 + 1, true, h)(scores)
        (scores, Math.min(left._2, right._2))         //int
      }
      (scores, 0)
    }
    val h = log2(scores.length)
    go(0, 0, true, h)(scores)._2

  }


        // Driver code
  def main(args: Array[String]): Unit = { // The number of elements in scores must be
    // a power of 2.
    val scores = List(3, 5, 2, 9)
    val res = MinMax(scores)
    println("The optimal value is : " + res)
    //val (max, min) = List(1, 5, 8, 9, 10, 12) partition ( _ >= 0 )
    //println(List(14, 35, -7, 46, 98).reduceLeft ( _ min _ ))
    //println(List(14, 35, -7, 46, 98).min)


    //println(List(14, 35, -7, 46, 98).reduceLeft ( _ max _ ))
    //println(List(14, 35, -7, 46, 98).max)
  }

}

// This article is contributed by vt_m
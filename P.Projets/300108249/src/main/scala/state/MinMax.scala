object MinMax {


  // Returns the optimal value a maximizer can obtain.
  // depth is current depth in game tree.
  // nodeIndex is index of current node in scores[].
  // isMax is true if current move is of maximizer, else false
  // scores[] stores leaves of Game tree.
  // h is maximum height of Game tree
  def minimax(depth: Int, nodeIndex: Int, isMax: Boolean, scores: Array[Int], h: Int): Int = {

    // Terminating condition. i.e leaf node is reached
    if (depth == h)
      scores(nodeIndex)
    else if (isMax)
    // If current move is maximizer, find the maximum attainable value
      Math.max(
        minimax(depth + 1, nodeIndex * 2, false, scores, h)
        , minimax(depth + 1, nodeIndex * 2 + 1, false, scores, h)
      )
    else
    // Else (If current move is Minimizer), find the minimum attainable value
      Math.min(
        minimax(depth + 1, nodeIndex * 2, true, scores, h)
        , minimax(depth + 1, nodeIndex * 2 + 1, true, scores, h)
      )

  }

  // A utility function to find Log n in base 2
  def log2(n: Int): Int =
    if (n == 1) 0
    else 1 + log2(n / 2)

  // Driver code
  def main(args: Array[String]): Unit = { // The number of elements in scores must be
    // a power of 2.
    val scores = Array(3, 5, 2, 9, 12, 5, 23, 23)
    val n = scores.length
    val h = log2(n)
    val res = minimax(0, 0, true, scores, h)
    println("The optimal value is : " + res)
  }
}

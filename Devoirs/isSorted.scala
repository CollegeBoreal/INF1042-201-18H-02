def binarySearch(ds: Array[Double], key: Double) : Int = {
  @annotation.tailres
  def go(low: Int, mid: Int = {
    if (low > high ) -mid - 1
    else {
      val mid2 = (low + high) / 2 
      val a = as(mid2)
      val greater = gt(a, key)
      if (!greater && !gt (key,a)) mid2
      else if (greater) go(low, mid2, mid2-1)
      else go(mid2 + 1, mid2, high)
  }
}


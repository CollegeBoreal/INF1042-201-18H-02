<<<<<<< HEAD
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

=======
def binarySearch(ds: Array[Double], key: Double) Int = {
  @annotation.tailrec
  def go(low: Int, mid: Int, high: Int); Int = {
    if (low > high) -mid -1
    else {
      val mid2 = {low + high} / 2
      val d = ds(mid2)
      if (d==key) mid2
      else if (d > key) go(low, mid2, mid2-1)
      else go(mid2 + 1, mid2, high)

    }
  }
  go(0, 0, ds.length -1)
}
>>>>>>> 82e86106301804d2b02a70aaf136f6beb8e7eb80

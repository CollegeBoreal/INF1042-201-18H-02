package state

import scalaz.State
//http://rosettacode.org/wiki/Count_the_coins#Scala: State Monad
object Project2 {

  type StateCoin = State[List[Int],Int]

  def count(target: Int): StateCoin = State{ coins =>
    if (target == 0) (coins, 1)
    else if (coins.isEmpty  || target < 0) (coins, 0)
    else (coins,count(target).eval(coins.tail) + count(target - coins.head).eval(coins))

  }

  def main(args: Array[String]): Unit = {

    // assert with run
    assert(count(15).run(List(2, 14, 19, 15))==(List(2, 14, 19, 15),1))
    assert(count(15).run(List(1,6,8,10))==(List(1, 6, 8, 10),6))
    assert(count(16).run(List(1,5,10, 15))==(List(1, 5, 10, 15),7))
    assert(count(15).run(List(1,7,10,16))==(List(1, 7, 10, 16),4))
    assert(count(18).run(List(1, 16, 18,30))==(List(1, 16, 18, 30),3))

    // assert with exec
    assert(count(16).exec(List(1,5,7, 9))==List(1, 5, 7, 9))
    assert(count(16).exec(List(1,5,7, 15))==List(1, 5, 7, 15))
    assert(count(16).exec(List(1,9,10, 18))==List(1, 9, 10, 18))
    assert(count(16).exec(List(1,9,10, 18))==List(1, 9, 10, 18))
    assert(count(16).exec(List(1,9,10, 18))==List(1, 9, 10, 18))
    println(count(18).exec(List(1,9,11, 21))==List(1, 9, 11, 21))
    // assert with eval
    assert(count(15).eval(List(1, 5, 10, 25))==6)
    assert(count(16).eval(List(1,5,10, 15))==7)
    assert(count(16).eval(List(1, 16, 18,20))==2)
    assert(count(12).eval(List(1,5,10, 15))==4)
    assert(count(12).eval(List(1,6,16,17))==3)
    assert(count(18).eval(List(1,9,11, 21))==4)

  }

}

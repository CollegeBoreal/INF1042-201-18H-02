# Library

## Create a new Scala project (with Git Bash [Windows] or Terminal [Mac])

```shell
$ sbt new scala/scala-seed.g8 --name=<my ID>
$ cd <my ID>
```

## Add the libraries

### scalaz

1. Add the scalaz library file

```shell
$ touch build-scalaz.sbt
```

2.  Add the scalaz library content (i.e. vi)

```scala
libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.20"
```

### scalaTest

1. Add the scalatest library file

```shell
$ touch build-test.sbt
```

2.  Add the scalatest library content (i.e. vi)

```scala
libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.5" % "test"
```

## Testing under sbt console

```scala
sbt:<my ID>> console
[info] Starting scala interpreter...
Welcome to Scala 2.12.5 (Java HotSpot(TM) 64-Bit Server VM, Java 1.8.0_151).
Type in expressions for evaluation. Or try :help.

scala> import scalaz.State
import scalaz.State

scala> val m1 = State { s: String => (s, s.length) }
m1: scalaz.State[String,Int] = scalaz.IndexedStateT$$anon$12@5568ed31

scala> m1.run("hello")==("hello", 5)
res0: Boolean = true

scala> def repeat(num: Int): State[String, Unit] = State { s: String => (s * num, ()) }
repeat: (num: Int)scalaz.State[String,Unit]

scala> repeat(3).run("abc")==("abcabcabc", ())
res1: Boolean = true

```




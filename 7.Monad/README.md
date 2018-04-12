# Library

## Create a new Scala project (with Git Bash [Windows] or Terminal [Mac])

Note: Replace MyID with your student ID

```shell
$ sbt new scala/scala-seed.g8 --name=`MyID`
$ cd `MyID`
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

Reference: https://softwarecorner.wordpress.com/2013/08/29/scalaz-state-monad/

1. run sbt

```shell
$ sbt
```

2. test

```scala
sbt:<my ID>> console
```

3. Execute

```
scala> import scalaz.State
scala> val m1 = State { s: String => (s, s.length) }
scala> m1.run("hello")
scala> def repeat(num: Int): State[String, Unit] = State { s: String => (s * num, ()) }
scala> repeat(3).run("abc")
```




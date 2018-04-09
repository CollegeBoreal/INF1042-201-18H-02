# Library

## Create a new Scala project (with IntelliJ)

```shell
$ sbt new scala/scala-seed.g8 --name=<my ID>
```

## Add the libraries

### scalaz

1. Add the scalaz library file

```shell
$ touch build-scalaz.sbt
```

2.  Add the scalaz library content

```scala
libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.20"
```

### scalaTest

1. Add the scalatest library file

```shell
$ touch build-test.sbt
```

2.  Add the scalatest library content

```scala
libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.5" % "test"
```




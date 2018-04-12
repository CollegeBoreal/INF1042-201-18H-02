# Library

## Create a new Scala project (with Git Bash [Windows] or Terminal [Mac])
   
* Position your prompt at the `7.Monad` directory

```shell
$ cd ~/Developer/INF1042-201-18H-02/7.Monad
```

* Create your project

Note: 
   
   Replace `MyID` with your student ID, ( no space after `--name=` )

```shell
$ sbt new scala/scala-seed.g8 --name=MyID
$ cd MyID  # Change Directory to your ID
```

## Add the libraries

### scalaz

* Add the scalaz library file . (Note: you can copy and paste the command in your terminal)

```shell
$ echo "libraryDependencies += \"org.scalaz\" %% \"scalaz-core\" % \"7.2.20\"" > build-scalaz.sbt
```

## Testing under sbt console

1. run sbt

```shell
$ sbt
```

2. test in Scala REPL

```scala
sbt:MyID> console
```

3. Execute

```scala> import scalaz.State```

import scalaz.State

```scala> val m1 = State { s: String => (s, s.length) }```

m1: scalaz.State[String,Int] = scalaz.IndexedStateT$$anon$12@237e4742

```scala> m1.run("hello")```

res0: scalaz.Id.Id[(String, Int)] = (hello,5)

## [BACK](./README.md)


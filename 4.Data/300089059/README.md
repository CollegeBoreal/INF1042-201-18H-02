
```scala

$ sbt run

[info]   Compilation completed in 8.425s.
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089059/src/main/scala/Tree.scala:34:1: expected class or object definition
[error] def fold[A, B](t: Tree[A])(f: A => B)(g: (B, B) => B): B = t match {
[error] ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089059/src/main/scala/Tree.scala:38:1: expected class or object definition
[error] def sizeViaFold[A](t: Tree[A]): Int =
[error] ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089059/src/main/scala/Tree.scala:43:3: expected class or object definition
[error]   def main(args: Array[String]): Unit = {
[error]   ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089059/src/main/scala/Tree.scala:69:5: Unmatched closing brace '}' ignored here
[error]     }
[error]     ^
[error] four errors found
[error] (Compile / compileIncremental) Compilation failed
[error] Total time: 10 s, completed Apr 4, 2018 8:23:28 AM
```


```scala

$ sbt run

...

[info] Compiling 2 Scala sources to /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089059/target/scala-2.12/classes ...
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089059/src/main/scala/Tree.scala:15:1: expected class or object definition
[error] def maximum(t: Tree[Int]): Int = t match {
[error] ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089059/src/main/scala/Tree.scala:21:1: expected class or object definition
[error] def depth[A](t: Tree[A]): Int = t match {
[error] ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089059/src/main/scala/Tree.scala:30:1: expected class or object definition
[error] def map[A, B](t: Tree[A])(f: A => B): Tree[B] = t match {
[error] ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089059/src/main/scala/Tree.scala:38:1: expected class or object definition
[error] def fold[A, B](t: Tree[A])(f: A => B)(g: (B, B) => B): B = t match {
[error] ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089059/src/main/scala/Tree.scala:44:1: expected class or object definition
[error] def main(args: Array[String]): Unit = {
[error] ^
[error] 5 errors found
[error] (Compile / compileIncremental) Compilation failed
[error] Total time: 0 s, completed Apr 2, 2018 9:27:20 PM

```



```scala

$ sbt run

...

[info] Compiling 2 Scala sources to /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300107710/target/scala-2.12/classes ...
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300107710/src/main/scala/Tree.scala:75:29: object Tree does not take type parameters.
[error]     assert(depthViaFold(Tree[Int])(Branch(Leaf(1),Leaf(2)),Leaf(3)) == 2)
[error]                             ^
[error] one error found
[error] (Compile / compileIncremental) Compilation failed
[error] Total time: 1 s, completed Apr 2, 2018 9:33:13 PM

```

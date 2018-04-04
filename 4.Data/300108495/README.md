
```scala

$ sbt run
...

Multiple main classes detected, select one to run:

 [1] List
 [2] Tree
[info] Packaging /Users/300098957/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300108495/target/scala-2.12/300108495_2.12-0.1.jar ...
[info] Done packaging.

Enter number: 2

[info] Running Tree 
3
[success] Total time: 17 s, completed 3-Apr-2018 3:26:47 PM
```

-------------

```scala

$ sbt run

...

[info] Compiling 2 Scala sources to /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300108495/target/scala-2.12/classes ...
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300108495/src/main/scala/tree.scala:65:29: object Tree does not take type parameters.
[error]     assert(depthViaFold(Tree[Int])(Branch(Leaf(1),Leaf(2)),Leaf(3)) == 2)
[error]                             ^
[error] one error found
[error] (Compile / compileIncremental) Compilation failed
[error] Total time: 1 s, completed Apr 2, 2018 9:23:50 PM

```

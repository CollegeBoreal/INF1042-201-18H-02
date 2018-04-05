

```scala
$ sbt run

...

[warn] Multiple main classes detected.  Run "show discoveredMainClasses" to see the list

Multiple main classes detected, select one to run:

 [1] List
 [2] Tree
[info] Packaging /Users/300098957/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/target/scala-2.12/300089781_2.12-0.1.jar ...
[info] Done packaging.

Enter number: 2

[info] Running Tree 
[success] Total time: 9 s, completed 3-Apr-2018 3:13:48 PM
```



```scala

$ sbt run 

[info] Compiling 2 Scala sources to /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/target/scala-2.12/classes ...
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:8:21: type mismatch;
[error]  found   : A
[error]  required: Int
[error]     case Leaf(n) => n
[error]                     ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:9:28: missing argument list for method maximum in trait Tree
[error] Unapplied methods are only converted to functions when a function type is expected.
[error] You can make this conversion explicit by writing `maximum _` or `maximum(_)` instead of `maximum`.
[error]     case Branch(l,r) => (l.maximum) max (r.maximum)
[error]                            ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:9:44: missing argument list for method maximum in trait Tree
[error] Unapplied methods are only converted to functions when a function type is expected.
[error] You can make this conversion explicit by writing `maximum _` or `maximum(_)` instead of `maximum`.
[error]     case Branch(l,r) => (l.maximum) max (r.maximum)
[error]                                            ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:15:32: missing argument list for method depth in trait Tree
[error] Unapplied methods are only converted to functions when a function type is expected.
[error] You can make this conversion explicit by writing `depth _` or `depth(_)` instead of `depth`.
[error]     case Branch(l,r) => 1 + (l.depth) max (r.depth)
[error]                                ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:15:46: missing argument list for method depth in trait Tree
[error] Unapplied methods are only converted to functions when a function type is expected.
[error] You can make this conversion explicit by writing `depth _` or `depth(_)` instead of `depth`.
[error]     case Branch(l,r) => 1 + (l.depth) max (r.depth)
[error]                                              ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:20:28: type mismatch;
[error]  found   : a.type (with underlying type A)
[error]  required: A
[error]     case Leaf(a) => Leaf(f(a))
[error]                            ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:21:38: type mismatch;
[error]  found   : A => B
[error]  required: Tree[?]
[error]     case Branch(l,r) => Branch(l.map(f), (r.map)(f))
[error]                                      ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:21:50: type mismatch;
[error]  found   : A => B
[error]  required: Tree[?]
[error]     case Branch(l,r) => Branch(l.map(f), (r.map)(f))
[error]                                                  ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:26:21: not found: value f
[error]     case Leaf(a) => f(a)
[error]                     ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:27:25: not found: value g
[error]     case Branch(l,r) => g(fold(l)(f)(g), fold(r)(f)(g))
[error]                         ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:27:35: not found: value f
[error]     case Branch(l,r) => g(fold(l)(f)(g), fold(r)(f)(g))
[error]                                   ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:27:38: not found: value g
[error]     case Branch(l,r) => g(fold(l)(f)(g), fold(r)(f)(g))
[error]                                      ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:27:50: not found: value f
[error]     case Branch(l,r) => g(fold(l)(f)(g), fold(r)(f)(g))
[error]                                                  ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:27:53: not found: value g
[error]     case Branch(l,r) => g(fold(l)(f)(g), fold(r)(f)(g))
[error]                                                     ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:44:37: missing argument list for method maximum in trait Tree
[error] Unapplied methods are only converted to functions when a function type is expected.
[error] You can make this conversion explicit by writing `maximum _` or `maximum(_)` instead of `maximum`.
[error]     assert(Branch(Leaf(2), Leaf(3)).maximum==3)
[error]                                     ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:45:14: not found: value maximum
[error]       assert(maximum(Branch(Leaf(1), Leaf(2))) == 2)
[error]              ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:46:14: not found: value maximum
[error]       assert(maximum(Branch(Branch(Leaf(10), Leaf(20)), Leaf(30))) == 30)
[error]              ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:49:14: not found: value depth
[error]       assert(depth(Leaf(10)) == 0)
[error]              ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:50:14: not found: value depth
[error]       assert(depth(Branch(Leaf(2.4), Leaf(1.3))) == 1)
[error]              ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:51:14: not found: value depth
[error]       assert(depth(Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))) == 2)
[error]              ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:54:14: not found: value map
[error]       assert(map(Leaf(10))(_ * 1.5) == Leaf(15.0))
[error]              ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:55:14: not found: value map
[error]       assert(map(Branch(Leaf(10), Leaf(20)))(_ * 1.5) == Branch(Leaf(15.0), Leaf(30.0)))
[error]              ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:56:14: not found: value map
[error]       assert(map(Branch(Branch(Leaf(10), Leaf(20)), Leaf(30)))(_ * 1.5) ==
[error]              ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:60:14: not found: value sizeViaFold
[error]       assert(sizeViaFold(Branch(Branch(Leaf(1),Leaf(2)),Leaf(3))) == 5)
[error]              ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:61:14: not found: value maximumViaFold
[error]       assert(maximumViaFold(Branch(Branch(Leaf(1),Leaf(2)),Leaf(3))) == 3)
[error]              ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:62:14: not found: value depthViaFold
[error]       assert(depthViaFold(Tree[Int])(Branch(Leaf(1),Leaf(2)),Leaf(3)) == 2)
[error]              ^
[error] /Users/valiha/Developer/CollegeBoreal/INF1042-201-18H-02/4.Data/300089781/src/main/scala/Tree.scala:62:31: object Tree does not take type parameters.
[error]       assert(depthViaFold(Tree[Int])(Branch(Leaf(1),Leaf(2)),Leaf(3)) == 2)
[error]                               ^
[error] 27 errors found
[error] (Compile / compileIncremental) Compilation failed
[error] Total time: 1 s, completed Apr 2, 2018 9:30:32 PM

```

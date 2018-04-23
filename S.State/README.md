# Category Theory

Basics of [Category Theory](https://en.wikipedia.org/wiki/Category_theory) such as:
  
  * categories, 
  * monoids, 
  * monads, 
  * functors, 
  * sum/product types,
  * types as propositions
  
  

 
# State Monad
 
## Definition

http://eed3si9n.com/learning-scalaz/State.html#State+and+StateT
 
We'll say that a stateful computation is a function that takes some state and returns a value along with some new state. That function would have the following type:
 
``` 
s -> (a, s)
```

Soit un état initial s (aussi appelé Seed) retournant (->) un nouvel état et une valeur de retour (a, s)
 
La valeur de retour et avec son nouvel état sont retourné sous forme de `tuple`. (a, s)
 
(je vous recommande d'éxécuter cet example sous le REPL scala) en tapant

```
$ sbt console
```

======================================
 
Pour rappel, un `tuple` est composé de 2 valeurs entourées de parenthèses   
 
```
scala> ("Etat",4)
res6: (String, Int) = (Etat,4)
``` 
=====================================
 
 
## STATE MONAD :
 
 
Le State Monad que l'on utilise a été écrit par la librairie scalaz
 
### Déclaration de l'état:
 
```
scala> import scalaz.State
import scalaz.State
``` 
 
### Construction de l'état:
 
Un exemple simple de State Monad où la liste d'entiers List[Int] est l'état et la valeur de retour un entier  Int
 
```
scala> State[List[Int], Int] { case x :: xs => (xs, x); case _ => (Nil,0) }
res0: scalaz.State[List[Int],Int] = scalaz.IndexedStateT$$anon$12@46af092d
```
 
Le State Monad ainsi construit retourne un tuple où :

*	La queue de la liste se retrouve dans le nouvel état et la tête dans la valeur de retour quand la liste n'est pas vide.     `(xs, x)`

*	La liste vide dans l'état et la valeur 0 dans la valeur de retour par défaut `(Nil,0)`
 
### Exécution de l'état:
 
*	Pour exécuter l'état en retournant l'état et sa valeur sous la forme d'un tuple on utilise run
 
```
scala> res0.run(List(1,2))
res7: scalaz.Id.Id[(List[Int], Int)] = (List(2),1)
``` 
 
```
scala> res0.run(Nil)
res2: scalaz.Id.Id[(List[Int], Int)] = (List(),0)
```
 
*	Pour exécuter l'état en retournant UNIQUEMENT sa valeur on utilise eval
 
 
```
scala> res0.eval(List(1,2))
res2: scalaz.Id.Id[Int] = 1
```
 
 
```
scala> res0.eval(Nil)
res5: scalaz.Id.Id[Int] = 0
```
 
*	Pour exécuter l'état en retournant UNIQUEMENT le nouvel état on utilise exec
 
 
```
scala> res0.exec(List(1,2))
res3: scalaz.Id.Id[List[Int]] = List(2)
```
 
 
```
scala> res0.exec(Nil)
res4: scalaz.Id.Id[List[Int]] = List()
```
 
# Reference:

Category Theory: https://plato.stanford.edu/entries/category-theory/

Intro to Scalaz by Michael Pilquist: https://speakerdeck.com/mpilquist/intro-to-scalaz

Scalaz State Monad by Michael Pilquist: https://speakerdeck.com/mpilquist/scalaz-state-monad

 
 
 



//  Generic Lists                                                               //
1 :: 2 :: "Bar":: "food" :: Nil                                                 //res0: List[Any] = List(1, 2, Bar, food)
                                                                                //
val rando = List("food", "cat", 1)                                              //rando: List[Any] = List(food, cat, 1)
                                                                                //
// We cannot recover type information                                           //
// about individual members:                                                    //
                                                                                //
rando.head  // Type of "food" is `Any`                                          //res1: Any = food
                                                                                //
def drop1[A](l: List[A]) = l.tail                                               //
                                                                                //drop1: drop1[A](val l: List[A]) => List[A]
drop1(List(2, 3, 4))                                                            //
drop1(List("a", "b", "c"))                                                      //res2: List[Int] = List(3, 4)
                                                                                //res3: List[String] = List(b, c)
// Why does this work?                                                          //
def drop2[Int](l: List[Int]): List[Int] = l.tail                                //
drop2(List(2, 3, 4))                                                            //drop2: drop2[Int](val l: List[Int]) => List[Int]
drop2(List("a", "b", "c"))                                                      //res4: List[Int] = List(3, 4)
// It works because we are not actually using                                   //res5: List[String] = List(b, c)
// Int, we are repurposing Int as a new type                                    //
// definition!                                                                  //
                                                                                //
// This is out how you define return type!                                      //
def mult(x: Int): Unit = println(x * 2)                                         //
def mult2(x: Int): Int = x * 2                                                  //mult: mult[](val x: Int) => Unit
mult2(2)                                                                        //mult2: mult2[](val x: Int) => Int
                                                                                //res6: Int = 4
// Rank-1 polymorphism                                                          //
                                                                                //
def toList[A](a: A) = List(a)                                                   //
toList(2)                                                                       //toList: toList[A](val a: A) => List[A]
toList('a')                                                                     //res7: List[Int] = List(2)
                                                                                //res8: List[Char] = List(a)
// This doesn't compile                                                         //
// def food[A, B](f: A => List[A], b: B) = f(b)                                 //
// even if we fix type `B`                                                      //
// def foo[A](f: A => List[A], i: Int) = f(i)                                   //
                                                                                //
// Type inference                                                               //
def id[T](x: T) = x                                                             //
val x = id(322)                                                                 //id: id[T](val x: T) => T
val y = id("hey")                                                               //x: Int = 322
var z = id(Array(1,2,3,4))                                                      //y: String = hey
// Types are preserved, without specifying return type                          //z: Array[Int] = Array(1, 2, 3, 4)
                                                                                //
// Variance                                                                     //
// --------                                                                     //
                                                                                //
class Covariant[+A]                                                             //
                                                                                //defined class Covariant
val cv: Covariant[AnyRef] = new Covariant[String]                               //
                                                                                //cv: Covariant[AnyRef] = Covariant@753f182d
// val fail: Covariant[String] = new Covariant[AnyRef]                          //
// error: type mismatch                                                         //
                                                                                //
class Contravariant[-A]                                                         //
val cv2: Contravariant[String] = new Contravariant[AnyRef]                      //defined class Contravariant
                                                                                //cv2: Contravariant[String] = Contravariant@1b8717ce
// val fail: Contravariant[AnyRef] = new Contravariant[String]                  //
                                                                                //
trait Function1[-T1, +R] extends AnyRef                                         //
                                                                                //defined trait Function1
class Animal { val sound = "rustle" }                                           //
class Bird extends Animal { override val sound = "call" }                       //defined class Animal
class Chicken extends Bird { override val sound = "cluck" }                     //defined class Bird
                                                                                //defined class Chicken
val getTweet: (Bird => String) = (a: Animal) => a.sound                         //
                                                                                //getTweet: Bird => String = <function1>
val a = new Animal                                                              //
val b = new Bird                                                                //a: Animal = Animal@40224d61
val c = new Chicken                                                             //b: Bird = Bird@4b01fdef
getTweet(a)                                                                     //c: Chicken = Chicken@77bb2d57
getTweet(b)                                                                     //res9: String = call
getTweet(c)                                                                     //res10: String = call
                                                                                //res11: String = call
val hatch: (() => Bird) = () => new Chicken                                     //
                                                                                //

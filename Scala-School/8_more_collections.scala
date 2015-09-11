// Lists                                                                        //
List(1, 2, 3)                                                                   //res0: List[Int] = List(1, 2, 3)
                                                                                //
// Scala also has cons, like Haskell                                            //
1 :: 2 :: 3 :: Nil                                                              //res1: List[Int] = List(1, 2, 3)
                                                                                //
// Sets                                                                         //
Set(1, 1, 2)                                                                    //res2: scala.collection.immutable.Set[Int] = Set(1, 2)
                                                                                //
// Sequences                                                                    //
Seq(1, 1, 2)                                                                    //res3: Seq[Int] = List(1, 1, 2)
// Lists are an implementation of Seq                                           //
                                                                                //
// Maps                                                                         //
Map('a' -> 1, 'b' -> 2)                                                         //res4: scala.collection.immutable.Map[Char,Int] = Map(a -> 1, b -> 2)
                                                                                //
// Traversable methods:                                                         //
                                                                                //
val m = Map(1 -> 'a', 2 -> 'b')                                                 //m: scala.collection.immutable.Map[Int,Char] = Map(1 -> a, 2 -> b)
m.filter((x: (Int, Char)) => x._1 >= 2)                                         //res5: scala.collection.immutable.Map[Int,Char] = Map(2 -> b)
                                                                                //
val s = Set(1, 3, 4, 5, 6, 5, 6)                                                //s: scala.collection.immutable.Set[Int] = Set(5, 1, 6, 3, 4)
s.partition((i: Int) => i >= 2)                                                 //res6: (scala.collection.immutable.Set[Int], scala.collection.immutable.Set[Int]) = (Set(5, 6, 3, 4),Set(1))
                                                                                //
// Conversion:                                                                  //
Map(1 -> 2).toArray                                                             //res7: Array[(Int, Int)] = Array((1,2))
Map(1 -> 2).toList                                                              //res8: List[(Int, Int)] = List((1,2))
                                                                                //
// Add                                                                          //
s + 9                                                                           //res9: scala.collection.immutable.Set[Int] = Set(5, 1, 6, 9, 3, 4)
m + (3 -> 'c')                                                                  //res10: scala.collection.immutable.Map[Int,Char] = Map(1 -> a, 2 -> b, 3 -> c)
                                                                                //
// Contains                                                                     //
s contains 12                                                                   //res11: Boolean = false
m.contains(1)                                                                   //res12: Boolean = true
                                                                                //
// Tuples -> construct is sugar for                                             //
"a" -> 2                                                                        //res13: (String, Int) = (a,2)
"a".->(2)                                                                       //res14: (String, Int) = (a,2)
                                                                                //
// You can also build maps via ++                                               //
Map.empty ++ List(("a", 1), ("b", 2), ("c", 3))                                 //res15: scala.collection.immutable.Map[String,Int] = Map(a -> 1, b -> 2, c -> 3)
                                                                                //
// Commonly-used Subclasses                                                     //
// Vectors: Fast random selection and fast updates                              //
IndexedSeq(1, 2, 3)                                                             //res16: IndexedSeq[Int] = Vector(1, 2, 3)
                                                                                //
// Range                                                                        //
for (i <- 1 to 3) { println(i) }                                                //1
                                                                                //2
                                                                                //3
                                                                                //res17: Unit = ()
                                                                                //
// Ranges have standard functional combinators available                        //
(1 to 3).map { i => i * 2}                                                      //res18: scala.collection.immutable.IndexedSeq[Int] = Vector(2, 4, 6)
                                                                                //
// Defaults                                                                     //
// Using apply methods on traits will give you an instance                      //
// of the default implementation:                                               //
                                                                                //
Iterable(1, 2)  // List!                                                        //res19: Iterable[Int] = List(1, 2)
Seq(1, 2)  // Also list!                                                        //
Set(1, 2)  // A set. Obviously?                                                 //res20: Seq[Int] = List(1, 2)
                                                                                //
// Mutable versus Immutable.                                                    //res21: scala.collection.immutable.Set[Int] = Set(1, 2)
                                                                                //
// We favour starting with immutable, and only                                  //
// switch to mutable if performance dictates.                                   //
                                                                                //
// Mutable                                                                      //
// -------                                                                      //
// (All of the above are immutable.)                                            //
                                                                                //
val numbers = collection.mutable.Map(1 -> 2)                                    //
numbers.get(1)                                                                  //
numbers.getOrElseUpdate(2, 4)                                                   //
numbers  // now contains (2, 4)                                                 //numbers: scala.collection.mutable.Map[Int,Int] = Map(1 -> 2)
                                                                                //res22: Option[Int] = Some(2)
numbers += (4 -> 1)                                                             //res23: Int = 4
                                                                                //res24: scala.collection.mutable.Map[Int,Int] = Map(2 -> 4, 1 -> 2)
// Try that with a regular dictionary                                           //
var im_numbers = Map(1 -> 2)                                                    //
im_numbers += (4 -> 1)  // returns a new Map                                    //res25: scala.collection.mutable.Map[Int,Int] = Map(2 -> 4, 4 -> 1, 1 -> 2)
im_numbers  // but is reassigned                                                //
                                                                                //
// im_numbers.getOrElseUpdate(2, 6)  // Doesn't exist                           //im_numbers: scala.collection.immutable.Map[Int,Int] = Map(1 -> 2)
                                                                                //res26: Unit = ()
// Life with Java:                                                              //
// ---------------                                                              //res27: scala.collection.immutable.Map[Int,Int] = Map(1 -> 2, 4 -> 1)
                                                                                //
// JavaConverters package decorates collections                                 //
// with asScala & asJava methods                                                //
import scala.collection.JavaConverters._                                        //
val sl = new scala.collection.mutable.ListBuffer[Int]                           //
val jl : java.util.List[Int] = sl.asJava                                        //
val sl2 : scala.collection.mutable.Buffer[Int] = js.asScala                     //
assert(sl eq sl2)                                                               //import scala.collection.JavaConverters._
                                                                                //sl: scala.collection.mutable.ListBuffer[Int] = ListBuffer()


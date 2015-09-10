// Define a list                                                                //
val numbers = List(1,2,3,4)                                                     //numbers: List[Int] = List(1, 2, 3, 4)
                                                                                //
// Define a function                                                            //
def isEven(i: Int): Boolean = {                                                 //isEven: isEven[](val i: Int) => Boolean
  i % 2 == 0                                                                    //
}                                                                               //
                                                                                //
def timesTwo(i: Int): Int = {                                                   //timesTwo: timesTwo[](val i: Int) => Int
  i * 2                                                                         //
}                                                                               //
                                                                                //
// Filters. How many ways are there to define predicate?                        //
numbers.filter((i:Int) => i % 2 == 0)                                           //res0: List[Int] = List(2, 4)
numbers.filter(_ % 2 == 0)                                                      //res1: List[Int] = List(2, 4)
numbers.filter(isEven)                                                          //res2: List[Int] = List(2, 4)
numbers.filter(isEven _)                                                        //res3: List[Int] = List(2, 4)
numbers.filter(isEven(_))                                                       //res4: List[Int] = List(2, 4)
                                                                                //
// Zip                                                                          //
List(1, 2, 3).zip(List("a", "b", "c"))                                          //res5: List[(Int, String)] = List((1,a), (2,b), (3,c))
                                                                                //
// Partitions                                                                   //
val lotta = List(1,2,3,4,5,6,7,8,9,10)                                          //lotta: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
lotta.partition((i:Int) => i %2 == 0)                                           //res6: (List[Int], List[Int]) = (List(2, 4, 6, 8, 10),List(1, 3, 5, 7, 9))
                                                                                //
// flatMap is cool:                                                             //
val nested = List(List(1,2,3,4), List(5,6,7,8))                                 //nested: List[List[Int]] = List(List(1, 2, 3, 4), List(5, 6, 7, 8))
nested.flatMap(x => x.map(_ * 2))                                               //res7: List[Int] = List(2, 4, 6, 8, 10, 12, 14, 16)
                                                                                //
// Which is the same as this:                                                   //
nested.map((x: List[Int]) => x.map(x => x * 3)).flatten                         //res8: List[Int] = List(3, 6, 9, 12, 15, 18, 21, 24)
                                                                                //
// This looks similar, but operations are backwards                             //
nested.flatten.map(x => x * 3)                                                  //res9: List[Int] = List(3, 6, 9, 12, 15, 18, 21, 24)
                                                                                //
// Define our own map using folds                                               //
def ourMap(inputs: List[Int], fn: Int => Int): List[Int] = {                    //ourMap: ourMap[](val inputs: List[Int],val fn: Int => Int) => List[Int]
  inputs.foldRight(List[Int]()) {  // Why right?                                //
    (x: Int, xs: List[Int]) => fn(x) :: xs                                      //
  }                                                                             //
}                                                                               //
                                                                                //
ourMap(lotta, timesTwo)                                                         //res10: List[Int] = List(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)
                                                                                //
// How to filter                                                                //
val extensions = Map("Steeve" -> 100, "Bob" -> 50, "Joe"-> 30)                  //extensions: scala.collection.immutable.Map[String,Int] = Map(Steeve -> 100, Bob -> 50, Joe -> 30)
extensions.filter((namePhone: (String, Int)) => namePhone._2 > 80)              //res11: scala.collection.immutable.Map[String,Int] = Map(Steeve -> 100)
                                                                                //
// Alternatively, use case for pattern matching:                                //
extensions.filter({case (name, extension) => extension > 40})                   //res12: scala.collection.immutable.Map[String,Int] = Map(Steeve -> 100, Bob -> 50)
                                                                                //

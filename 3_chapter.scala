object Chapter3 {
  def main3(args: Array[String]) {
    // Can import java stuff
    val big = new java.math.BigInteger("12345")

    // Arrays
    val greetStrings = new Array[String](3)

    greetStrings(0) = "Hello"
    greetStrings(1) = ", "
    greetStrings(2) = "world!\n"

    for (i <- 0 to 2)
      print(greetStrings(i))

    // Far less verbose:
    val numNames = Array("zero", "one", "two")

    // Is equivalent to:
    val numNames2 = Array.apply("zero", "one", "cat")

    // Lists are immutable, unlike arrays:
    val oneTwoThree = List(1, 2, 3)

    // Operations on list must return new lists:
    val oneTwo = List(1, 2)
    val threeFour = List(3, 4)
    val oneTwoThreeFour = oneTwo ::: threeFour  // concat method
    println("" + oneTwo + " and " + threeFour + " were not mutated.")
    println("Thus, " + oneTwoThreeFour + " is a new list.")

    // :: is the cons operator:
    val twoThree = List(2, 3)
    val oneTwoThree2 = 1 :: twoThree
    println(oneTwoThree2)

    val oneTwoThree3 = 1 :: 2 :: 3 :: Nil
    println(oneTwoThree3)

    println("It's working!\n")

    // List filtering
    val thrill = List("Hat", "Cat", "Sat", "Rat", "Matt")
    println(thrill.filter(s => s.length == 4))

    // Tuples:
    val pair = (99, "Luftballons")
    println(pair._1) // Note that tuple indices are 1-based!
    println(pair._2)

    // Sets & Maps
    var jetSet = Set("Boeing", "Airbus")  // Note 'var'
    jetSet += "Lear"  // jetSet was not mutated here, but is a new set
    println(jetSet.contains("Cessna"))

    // To make a mutable set:
    import scala.collection.mutable.Set
    val movieSet = Set("Hitch", "Poltegeist")
    movieSet += "Shrek"  // In this case movieSet *was* mutated
    println(movieSet)

    // Maps
    // maps can also be mutable or immutable
    // e.g. import scala.collection.mutable.Map
    // or just use immutable map:
    val romanNumeral = Map(1 -> "I", 2 -> "II", 3 -> "III")
    println(romanNumeral(3))  // Note 3 is a key, not an index

    // Think functional
    //
    // This function is in imperative style:
    def printArgsImperative(args: Array[String]): Unit = {
      var i = 0
      while (i < args.length) {
        println(args(i))
        i += 1
      }
    }
    // Could be written as:
    def printArgs(args: Array[String]): Unit = {
      args.foreach(println)
    }

    def formatArgs(args: Array[String]) = args.mkString("\n")

    val res = formatArgs(Array("zero", "one", "two"))
    assert(res == "zero\none\ntwo")

    // Reading from a file
    import scala.io.Source

    def widthOfLength(s: String) = s.length.toString.length

    val lines = Source.fromFile("README.mkd").getLines.toList

    val longestLine = lines.reduceLeft((a, b) => if (a.length > b.length) a else b)
    val maxWidth = widthOfLength(longestLine)

    // Or determine max width in imperative form:
    var maxWidth2 = 0
    for (line <- lines)
      maxWidth2 = maxWidth2.max(widthOfLength(line))

    for (line <- lines) {
      val numSpaces = maxWidth - widthOfLength(line)
      val padding = " " * numSpaces
      print(padding + line.length + "| " + line + "\n")
    }
  }
}

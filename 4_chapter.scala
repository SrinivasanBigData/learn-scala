object Chapter4 {
  def main(args: Array[String]) {

    class ChecksumAccumulator {
      private var sum = 0
      def add(b: Byte): Unit = sum += b
      def checksum(): Int = return ~(sum & 0xFF) + 1
    }

    val acc = new ChecksumAccumulator
    val csa = new ChecksumAccumulator

    // If acc was was public, we could modify the contents of acc,
    // even though it is a val
    // acc.sum = 3  // Did not affect csa

    // However we can't do this: (Reassignment to val error)
    // acc = new ChecksumAccumulator
    //
    // Methods with result type "Unit" can be expressed like this:
    class Tester {
      private var sum = 0
      def add(b: Int) { sum += b }  // Curly braces
      def get() = sum.toString
    }

    val t = new Tester
    t.add(5)
    println("GET " + t.get)
  }
}


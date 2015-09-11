// Function composition                                                         //
def f(s: String) = "f(" + s + ")"                                               //f: f[](val s: String) => String
                                                                                //
def g(s: String) = "g(" + s + ")"                                               //g: g[](val s: String) => String
                                                                                //
val fComposeG = f _ compose g _                                                 //fComposeG: String => String = <function1>
fComposeG("Yay")                                                                //res0: String = f(g(Yay))
                                                                                //
// similarly, but inside out:                                                   //
val fThenG = f _ andThen g _                                                    //fThenG: String => String = <function1>
fThenG("boo")                                                                   //res1: String = g(f(boo))
                                                                                //
val two: PartialFunction[Int, String] = { case 2 => "two" }                     //two: PartialFunction[Int,String] = <function1>
val three: PartialFunction[Int, String] = {case 3 => "three"}                   //three: PartialFunction[Int,String] = <function1>
                                                                                //
val wild: PartialFunction[Int, String] = {case _ => "something"}                //wild: PartialFunction[Int,String] = <function1>
                                                                                //
val partial = two orElse three orElse wild                                      //partial: PartialFunction[Int,String] = <function1>
partial(5)                                                                      //res2: String = something
partial(2)                                                                      //res3: String = two
                                                                                //
                                                                                //
// Example "the mystery of case"                                                //
                                                                                //
case class PhoneExt(name: String, ext: Int)                                     //defined class PhoneExt
val extensions = List(PhoneExt("mitch", 5), PhoneExt("Trevor", 7))              //extensions: List[PhoneExt] = List(PhoneExt(mitch,5), PhoneExt(Trevor,7))
                                                                                //
extensions.filter { case PhoneExt(name, extension) => extension < 6 }           //res4: List[PhoneExt] = List(PhoneExt(mitch,5))
                                                                                //

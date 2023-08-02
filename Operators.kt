fun main() {
    val x=20
    val y=3
    var z=5
    println("x -> $x")
    println("y -> $y")
    println("z -> $z")
    println(">>>> Arithmetic Operator <<<<")
    println("x + y  -> "+(x+y))
    println("x - y  -> "+(x-y))
    println("x * y  -> "+(x*y))
    println("x / y  -> "+(x/y))
    println("x % y  -> "+(x%y))
    println("+ + z  -> "+(++z))
    println("- - z  -> "+(--z))
    println(">>>> Comparison  Operator <<<<")
    println("x > y  -> "+ (x>y))
    println("x < y  -> "+ (x<y))
    println("x == y -> "+ (x==y))
    println("x != y -> "+ (x!=y))
    println("x >= y -> "+ (x>=y))
    println("x <= y -> "+ (x<=y))
    println(">>>>>> Logical Operator <<<<<<")
    println("x < 5 && x < 10      -> "+ (x < 5 && x < 10))
    println("x < 5 || x < 4       -> "+ (x < 5 || y < 4))
    println("! ( x < 5 || x < 4 ) -> "+ !(x < 5 || y < 4))


}
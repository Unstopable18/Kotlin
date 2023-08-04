fun main() {
    // Read only map
    val readOnlyShapes = mapOf("triangle" to 3, "square" to 4, "circle" to 0)
    println(readOnlyShapes)

    // Mutable map with explicit type declaration
    val shapes: MutableMap<String, Int> = mutableMapOf("triangle" to 3, "square" to 4, "circle" to 0)
    println(shapes)

    println("Get item with key            -> ${readOnlyShapes["square"]}")
    println("Get Count of item            -> ${readOnlyShapes.count()}")
    println("Get key(circle) exists (in)  -> ${"circle" in readOnlyShapes}")
    println("Get containsKey (circle)     -> ${readOnlyShapes.containsKey("circle")}")
    println("Get keys                     -> ${readOnlyShapes.keys}")
    println("Get values                   -> ${readOnlyShapes.values}")

//    readOnlyShapes.put("pentagon",5)  // add
//    println("Added to ReadOnlyMap         -> $readOnlyShapes") // throws error as map is not set mutable
    shapes.put("pentagon",5) // add
    println("Added to MutableMap          -> $shapes")

//    readOnlyShapes.remove("pentagon")  // remove
//    println("Removed from ReadOnlyMap     -> $readOnlyShapes") // throws error as map is not set mutable
    shapes.remove("pentagon") // remove
    println("Removed to MutableMap        -> $shapes")

    // Practice
    val number2word = mapOf(1 to "ONE",2 to "TWO", 3 to "THREE")
    val n = 2
    println("$n is spelt as '${number2word[n]}'")



}
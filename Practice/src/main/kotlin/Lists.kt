fun main() {
    // Read only list
    val readOnlyShapes = listOf("triangle", "square", "circle")
    println(readOnlyShapes)

    // Mutable list with explicit type declaration
    val shapes: MutableList<String> = mutableListOf("triangle", "square", "circle")
    println(shapes)

    println("Get item with index 1        -> ${readOnlyShapes[1]}")
    println("Get First item               -> ${readOnlyShapes.first()}")
    println("Get Last item                -> ${readOnlyShapes.last()}")
    println("Get Count of item            -> ${readOnlyShapes.count()}")
    println("Get item (circle) exists     -> ${"circle" in readOnlyShapes}")

//    readOnlyShapes.add("pentagon")  // add
//    println("Added to ReadOnlyList        -> $readOnlyShapes") // throws error as list is not set mutable
    shapes.add("pentagon") // add
    println("Added to MutableList         -> $shapes")

//    readOnlyShapes.remove("pentagon")  // remove
//    println("Removed from ReadOnlyList    -> $readOnlyShapes") // throws error as list is not set mutable
    shapes.remove("pentagon") // remove
    println("Removed to MutableList       -> $shapes")


    // Practice
    val greenNumbers = listOf(1, 4, 23)
    val redNumbers = listOf(17, 2)
    // Write your code here
    println("${greenNumbers.count()+redNumbers.count()}")

}
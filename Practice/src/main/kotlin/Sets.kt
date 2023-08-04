fun main() {
    // Read only set
    val readOnlyShapes = setOf("triangle", "square", "circle")
    println(readOnlyShapes)

    // Mutable set with explicit type declaration
    val shapes: MutableSet<String> = mutableSetOf("triangle", "square", "circle")
    println(shapes)

    println("Get Count of item            -> ${readOnlyShapes.count()}")
    println("Get item (circle) exists     -> ${"circle" in readOnlyShapes}")

//    readOnlyShapes.add("pentagon")  // add
//    println("Added to ReadOnlySet        -> $readOnlyShapes") // throws error as set is not set mutable
    shapes.add("pentagon") // add
    println("Added to MutableSet         -> $shapes")

//    readOnlyShapes.remove("pentagon")  // remove
//    println("Removed from ReadOnlySet    -> $readOnlyShapes") // throws error as set is not set mutable
    shapes.remove("pentagon") // remove
    println("Removed to MutableSet       -> $shapes")

    // Practice
    val supportedSets = setOf("HTTP", "HTTPS", "FTP")
    val requested = "smtp"
    val isSupported = requested in supportedSets
    println("Support for $requested: $isSupported")




}

// Lambda Function
fun uppercaseString(string: String)= string.uppercase()

// Function with default argument values
fun intervalInSeconds(hours: Int=0, minutes: Int=0, seconds: Int=0) =
    ((hours * 60) + minutes) * 60 + seconds

fun repeatN(n: Int, action: () -> Unit) {
    for (i in 1..n) {
        action()
    }
}

fun main() {

    println(uppercaseString("hello"))
    val uppercaseStringVal={ string: String -> string.uppercase() }
    println(uppercaseStringVal("hello"))
    println(intervalInSeconds(hours=1, minutes=20, seconds=15))
    println(intervalInSeconds(minutes=1, seconds=25))
    println(intervalInSeconds(hours=2))
    println(intervalInSeconds(minutes=10))
    println(intervalInSeconds(hours=1,seconds=1))

    // filter
    val numbers= listOf(1,-2,3,-4,5,-6)
    val positives=numbers.filter{x->x>0}
    println(positives)
    val negatives=numbers.filter{x->x<0}
    println(negatives)
    // Trailing lambda with .fold()
    println(listOf(1, 2, 3).fold(0, { x, item -> x + item }))

    //Practice
    val actions = listOf("title", "year", "author")
    val prefix = "https://example.com/book-info"
    val idNum= 5
    val urlMaps = actions.map { action -> "$prefix/$idNum/$action" }
    println(urlMaps)
    repeatN(5) {
        println("Hello")
    }
}
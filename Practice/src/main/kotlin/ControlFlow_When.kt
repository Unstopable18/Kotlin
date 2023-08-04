fun main() {
    val obj="Hello"
    when(obj){
        "1"-> println("One")
        "Hello"-> println("Greeting")
        else-> println("Unknown")
    }

    val temp=18
    val description=when{
        temp<0->"Very Cold"
        temp<10->"A bit Cold"
        temp<20->"Warm"
        else->"Hot"
    }
    println(description)

    // Practice
    val button = "Y"
    println(
        when (button){
            "A"->"Yes"
            "B"->"No"
            "X"->"Menu"
            "Y"->"Nothing"
            else->"There is no such button"
        }
    )
}
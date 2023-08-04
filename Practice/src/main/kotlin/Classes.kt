class Classes (val id: Int, var email: String = "example@gmail.com") {
    val category: String = "work"
    fun printId(){
        println(id)
    }
}

data class User(val name: String, val id: Int)

fun main() {
    val contact=Classes(1,"marry@gmail.com")
    println(contact.email)
//    contact.id=3 // Error val declared can't change
    contact.email="jane@gmail.com" // var can be updated
    println(contact.email)

    contact.printId()

    // data class
    val userA=User("Jane",1)
    val userB=User("Merry",2)
    val userC=User("Jane",1)
    // print to string data class
    println(userA)
    // compare instance data class
    println("userA==userB -> ${userA==userB}")
    println("userA==userC -> ${userA==userC}")

    // copy instance data class
    println(userA.copy())
    println(userA.copy("Max"))
    println(userA.copy(id=3))

}
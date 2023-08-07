import org.jetbrains.annotations.NotNull
data class Employees (val name: String, var salary: Int)

fun employeeById(id: Int) = when(id) {
    1 -> Employee("Mary", 20)
//    2 -> null
    3 -> Employee("John", 21)
    4 -> Employee("Ann", 23)
    else -> null
}

fun salaryById(id: Int)= employeeById(id)?.salary?:0
fun main() {

    var nullable:String?="This variable with Data Type can be null"
    nullable=null
    // By default null values are not accepted
    var neverNull="This variable cannot be null"
//    neverNull=null  // throws error
    var neverNullDtype:String="This variable with Data Type cannot be null"
//    neverNullDtype=null  // throws error

//    fun strLength(notNull: String?): String {
//        if(notNull!=null && notNull.length>0) {
//            return "String of length ${notNull.length}"
//        } else {
//            return "Empty or null string"
//        }
//    }

    // Use safe calls
    fun  strLength(notNull: String?):Int?=notNull?.length

    println(strLength(neverNull))
    println(strLength(neverNullDtype))
    println(strLength(nullable))

//  ?: value to five default to null value
    var nullString: String? = null
    println(nullString?.length ?: 0)


    // Practice

    println((1..5).sumOf { id -> salaryById(id) })

}


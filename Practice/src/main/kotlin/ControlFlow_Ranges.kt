fun main() {
    println("\n'z' downTo 'a' step 5")
    for(i in 'z' downTo 'a' step 5){
        print("  ")
        print(i)
    }
    println("\n100 downTo 1 step 7")
    for(j in 100 downTo 1 step 7){
        print("  ")
        print(j)
    }
    println("\n'a'..'z' step 5")
    for (k in 'a'..'z' step 5){
        print("  ")
        print(k)
    }
    println("\n1..100 step 7")
    for(l in 7..100 step 7){
        print("  ")
        print(l)
    }
}
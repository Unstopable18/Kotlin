fun main() {

    //For Loop
    for(i in 3..10 step 3){
        println(i)
    }

    val cakes= listOf("carrot","cheese","chocolate","raspberry","blueberry")
    for(cake in cakes){
        println("Yum yum, it's a $cake cake.")
    }

    // While loop
    var cakesEaten=0
    var cakesBaked=0
    while (cakesEaten<3){
        println("Eat a cake..")
        cakesEaten++
    }

    // do-while Loop
    do{
        println("Bake a Cake.")
        cakesBaked++
    }while (cakesBaked<cakesEaten)

//    Practice
    // while
    var pizzaSlices = 1
    // Start refactoring here
    while(pizzaSlices<8) {
        println("There's only $pizzaSlices slice/s of pizza :(")
        pizzaSlices++
    }
    println("There are $pizzaSlices slices of pizza. Hooray! We have a whole pizza! :D")

    // do-while
    pizzaSlices = 1
    do{
        println("There's only $pizzaSlices slice/s of pizza :(")
        pizzaSlices++
    }while(pizzaSlices<8)
    println("There are $pizzaSlices slices of pizza. Hooray! We have a whole pizza! :D")

    // for id else if
    for(i in 1..100){
        if (i%3==0 && i%5==0){
            println("fizzbuzz")
        }else if (i%3==0){
            println("fizz")
        }else if (i%5==0){
            println("buzz")
        }else{
            println(i)
        }
    }

    // for when
    for (number in 1..100) {
        println(
            when {
                number % 15 == 0 -> "fizzbuzz"
                number % 3 == 0 -> "fizz"
                number % 5 == 0 -> "buzz"
                else -> number.toString()
            }
        )
    }

    // for if
    val words = listOf("dinosaur", "limousine", "magazine", "language")
    // Write your code here
    for(i in words){
        if(i[0]=='l'){
            println(i)
        }
    }

    for(i in words){
        if(i.startsWith('l')){
            println(i)
        }
    }

}
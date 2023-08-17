package com.example.quizapp

object Constants {
    const val USER_NAME:String="USERNAME"
    const val TOTAL_QUES:String="TOTAL_QUESTIONS"
    const val CORRECT_ANS:String="CORRECT_ANSWERS"

    fun getQues():ArrayList<Questions>{
        val queList=ArrayList<Questions>()
        val que1=Questions(1,"What country does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "Australia","Brazil","India","Argentina",2)
        queList.add(que1)
        val que2=Questions(2,"What country does this flag belong to?",
            R.drawable.ic_flag_of_australia,
            "Australia","Brazil","India","Argentina",1)
        queList.add(que2)
        val que3=Questions(3,"What country does this flag belong to?",
            R.drawable.ic_flag_of_kuwait,
            "Fiji","Brazil","Kuwait","Argentina",3)
        queList.add(que3)
        val que4=Questions(4,"What country does this flag belong to?",
            R.drawable.ic_flag_of_new_zealand,
            "India","Brazil","New Zealand","Argentina",3)
        queList.add(que4)
        val que5=Questions(5,"What country does this flag belong to?",
            R.drawable.ic_flag_of_denmark,
            "Australia","Brazil","India","Denmark",4)
        queList.add(que5)
        val que6=Questions(6,"What country does this flag belong to?",
            R.drawable.ic_flag_of_india,
            "Fiji","Brazil","India","Denmark",3)
        queList.add(que6)
        val que7=Questions(7,"What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina,
            "India","Brazil","New Zealand","Argentina",4)
        queList.add(que7)
        val que8=Questions(8,"What country does this flag belong to?",
            R.drawable.ic_flag_of_fiji,
            "Fiji","Brazil","India","Denmark",1)
        queList.add(que8)
        val que9=Questions(9,"What country does this flag belong to?",
            R.drawable.ic_flag_of_belgium,
            "Belgium","Brazil","India","Argentina",1)
        queList.add(que9)
        return queList
    }
}
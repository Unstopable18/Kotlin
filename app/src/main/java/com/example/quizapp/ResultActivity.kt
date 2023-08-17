package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val nameTxtView:TextView=findViewById(R.id.usernameTxtView)
        val scoreTxtView:TextView=findViewById(R.id.scoreTxtView)
        val btnFinish:Button=findViewById(R.id.btnFinish)

        val mCorrectAns=intent.getIntExtra(Constants.CORRECT_ANS,0)
        val mTotalQue=intent.getIntExtra(Constants.TOTAL_QUES,0)

        nameTxtView.text=intent.getStringExtra(Constants.USER_NAME)
        scoreTxtView.text="Your Score is $mCorrectAns out of $mTotalQue"
        btnFinish.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}
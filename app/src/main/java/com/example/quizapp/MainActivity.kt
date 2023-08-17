package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart:Button=findViewById(R.id.btnStart)
        val nameEditTxt:EditText=findViewById(R.id.nameEditTxt)
        btnStart.setOnClickListener{
            if(nameEditTxt.text.isEmpty()){
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show()
            }else{
//                Jump from one page to other
                val intent=Intent(this,QuizQuestionActivity::class.java)
                intent.putExtra(Constants.USER_NAME,nameEditTxt.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}
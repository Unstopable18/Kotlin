package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener{

    private var mCurrentPos:Int=1
    private var mQueList:ArrayList<Questions>?=null
    private var mSelectedOptPos:Int=0
    private var mUsername:String?=null
    private var mCorrectAns:Int=0

    private var progressBar:ProgressBar?=null
    private var progressTxtView:TextView?=null
    private var queTxtView:TextView?=null
    private var imageView:ImageView?=null

    private var opt1TxtView:TextView?=null
    private var opt2TxtView:TextView?=null
    private var opt3TxtView:TextView?=null
    private var opt4TxtView:TextView?=null
    private var submitBtn:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        mUsername=intent.getStringExtra(Constants.USER_NAME)

        progressBar=findViewById(R.id.progressBar)
        progressTxtView=findViewById(R.id.progressTxtView)
        queTxtView=findViewById(R.id.queTxtView)
        imageView=findViewById(R.id.imageView)

        opt1TxtView=findViewById(R.id.option1)
        opt2TxtView=findViewById(R.id.option2)
        opt3TxtView=findViewById(R.id.option3)
        opt4TxtView=findViewById(R.id.option4)
        submitBtn=findViewById(R.id.btnSubmit)

        opt1TxtView?.setOnClickListener(this)
        opt2TxtView?.setOnClickListener(this)
        opt3TxtView?.setOnClickListener(this)
        opt4TxtView?.setOnClickListener(this)
        submitBtn?.setOnClickListener(this)

        mQueList = Constants.getQues()
        //        Log.i("No. of questions in list","${queList.size}")
        //        for (i in queList){
        //            Log.e("Questions",i.question)
        //        }
        setQue()
    }

    private fun setQue() {
        defaultOptView()
        val question: Questions = mQueList!![mCurrentPos - 1]
        imageView?.setImageResource(question.image)
        progressBar?.progress = mCurrentPos
        progressTxtView?.text = "$mCurrentPos/${progressBar?.max}"
        queTxtView?.text = question.question
        opt1TxtView?.text = question.optionOne
        opt2TxtView?.text = question.optionTwo
        opt3TxtView?.text = question.optionThree
        opt4TxtView?.text = question.optionFour
        if(mCurrentPos==mQueList!!.size){
            submitBtn?.text="FINISH"
        }else{
            submitBtn?.text="SUBMIT"
        }
    }

    private fun defaultOptView(){
        val options= ArrayList<TextView>()
        opt1TxtView?.let { options.add(0,it) }
        opt2TxtView?.let { options.add(1,it) }
        opt3TxtView?.let { options.add(2,it) }
        opt4TxtView?.let { options.add(3,it) }
        for(opt in options){
            opt.setTextColor(Color.parseColor("#15616d"))
            opt.typeface= Typeface.DEFAULT
            opt.background=ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)
        }
    }

    private fun selectedOptView(txtView: TextView,selectedOptNum:Int){
        defaultOptView()
        mSelectedOptPos=selectedOptNum
        txtView.setTextColor(Color.parseColor("#edae49"))
        txtView.setTypeface(txtView.typeface,Typeface.BOLD)
        txtView.background=ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.option1->{
                opt1TxtView?.let { selectedOptView(it,1) }
            }
            R.id.option2->{
                opt2TxtView?.let { selectedOptView(it,2) }
            }
            R.id.option3->{
                opt3TxtView?.let { selectedOptView(it,3) }
            }
            R.id.option4->{
                opt4TxtView?.let { selectedOptView(it,4) }
            }
            R.id.btnSubmit->{
                if(mSelectedOptPos==0){
                    mCurrentPos++
                    when{
                        mCurrentPos<=mQueList!!.size->{
                            setQue()
                        }
                        else->{
                            val intent=Intent(this,ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME,mUsername)
                            intent.putExtra(Constants.CORRECT_ANS,mCorrectAns)
                            intent.putExtra(Constants.TOTAL_QUES,mQueList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }else{
                    val question=mQueList?.get(mCurrentPos-1)
                    if(question!!.correctAns!=mSelectedOptPos){
                        ansView(mSelectedOptPos,R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAns++
                    }
                    ansView(question.correctAns,R.drawable.correct_option_border_bg)
                    if (mCurrentPos==mQueList!!.size){
                        submitBtn?.text="FINISH"
                    }else{
                        submitBtn?.text="NEXT QUESTION"
                    }
                    mSelectedOptPos=0
                }
            }
        }
    }

    private fun ansView(ans:Int,drawableView:Int){
        when(ans){
            1->{opt1TxtView?.background=ContextCompat.getDrawable(this,drawableView)}
            2->{opt2TxtView?.background=ContextCompat.getDrawable(this,drawableView)}
            3->{opt3TxtView?.background=ContextCompat.getDrawable(this,drawableView)}
            4->{opt4TxtView?.background=ContextCompat.getDrawable(this,drawableView)}
        }
    }
}
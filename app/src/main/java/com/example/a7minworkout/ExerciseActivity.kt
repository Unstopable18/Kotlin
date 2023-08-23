package com.example.a7minworkout

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.a7minworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private var binding:ActivityExerciseBinding?=null
    private var restTimer:CountDownTimer?=null
    private var restProgress=0
    private var exerciseTimer:CountDownTimer?=null
    private var exerciseProgress=0
    private var exerciseList:ArrayList<ExerciseModel>?=null
    private var currentExercisePos=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolBarExercise)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        exerciseList=Constants.defaultExerciseList()
        binding?.toolBarExercise?.setNavigationOnClickListener{
            onBackPressed()
        }
//        AppInitializer.getInstance(this).initializeComponent(RiveInitializer::class.java)
//        Rive.init(this@ExerciseActivity)

        setupRestView()

    }
    private fun setupRestView(){
        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }
        setRestProgressbar()
    }
    private fun setupExerciseView(){
        binding?.ivGif?.visibility=View.GONE
        binding?.flProgressBar?.visibility= View.GONE
        binding?.tvTitle?.text="EXERCISE"

        val layoutParams = binding?.tvTitle?.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.bottomToTop = binding?.flExerciseBar?.id!!
        binding?.tvTitle?.layoutParams=layoutParams
        binding?.tvTitle?.requestLayout()

        binding?.flExerciseBar?.visibility= View.VISIBLE
        if(exerciseTimer!=null){
            exerciseTimer?.cancel()
            exerciseProgress=0
        }
        setExerciseProgressbar()
    }

    private fun setRestProgressbar(){
        binding?.progressBar?.progress=restProgress
        restTimer=object:CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress=10-restProgress
                binding?.tvTimer?.text=(10-restProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity, "Lets start the Exercise.", Toast.LENGTH_SHORT).show()
                currentExercisePos++
                setupExerciseView()
            }
        }.start()
    }
    private fun setExerciseProgressbar(){
        binding?.exerciseBar?.progress=exerciseProgress
        exerciseTimer=object:CountDownTimer(30000,1000){
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding?.exerciseBar?.progress=30-exerciseProgress
                binding?.tvExerciseTimer?.text=(30-exerciseProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity, "30 sec Exercise completed.", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }
    override fun onDestroy() {
        super.onDestroy()
        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }
        if(exerciseTimer!=null){
            exerciseTimer?.cancel()
            exerciseProgress=0
        }
        binding=null
    }
}
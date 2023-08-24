package com.example.a7minworkout

import android.app.Activity
import android.content.Intent
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

        setupRestView()

    }
    private fun setupRestView(){
//        binding?.ll1?.visibility=View.VISIBLE
//        binding?.ivGif?.visibility=View.VISIBLE
//        binding?.tvTitle?.visibility=View.VISIBLE
        binding?.tvTitle?.text="GET READY FOR EXERCISE"
//        binding?.flProgressBar?.visibility= View.VISIBLE

//        val layoutParams = binding?.tvTitle?.layoutParams as ConstraintLayout.LayoutParams
//        layoutParams.bottomToTop = binding?.flProgressBar?.id!!
//        binding?.tvTitle?.layoutParams=layoutParams
//        binding?.tvTitle?.requestLayout()
//        binding?.ll2?.visibility=View.GONE
//        binding?.flExerciseBar?.visibility= View.GONE

        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }

        setRestProgressbar()
    }
    private fun setupExerciseView(){
//        binding?.ll1?.visibility=View.GONE
//        binding?.ivGif?.visibility=View.VISIBLE
//        binding?.tvTitle?.visibility=View.VISIBLE
//        binding?.flProgressBar?.visibility= View.VISIBLE
        binding?.progressBar?.max=30
        binding?.tvTimer?.text=30.toString()

        binding?.tvUpExercise?.visibility=View.GONE
        binding?.tvTitle?.text=exerciseList!![currentExercisePos].getName()
//        binding?.flExerciseBar?.visibility= View.VISIBLE

//        binding?.ll2?.visibility=View.VISIBLE
//        binding?.ivGifExercise?.visibility=View.VISIBLE
        binding?.ivGif?.setImageResource(exerciseList!![currentExercisePos].getImage())

//        val layoutParams = binding?.tvTitle?.layoutParams as ConstraintLayout.LayoutParams
//        layoutParams.bottomToTop = binding?.flExerciseBar?.id!!
//        binding?.tvTitle?.layoutParams=layoutParams
//        binding?.tvTitle?.requestLayout()

        if(exerciseTimer!=null){
            exerciseTimer?.cancel()
            exerciseProgress=0
        }
        setExerciseProgressbar()
    }

    private fun setRestProgressbar(){

        binding?.progressBar?.progress=restProgress
        restTimer=object:CountDownTimer(3000,1000){
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
        binding?.progressBar?.progress=exerciseProgress
        exerciseTimer=object:CountDownTimer(5000,1000){
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding?.progressBar?.progress=30-exerciseProgress
                binding?.tvTimer?.text=(30-exerciseProgress).toString()
            }

            override fun onFinish() {
                if(currentExercisePos < exerciseList?.size!! - 1){
//                    binding?.ivGif?.visibility=View.VISIBLE
                    binding?.ivGif?.setImageResource(R.drawable.animation_break)
                    binding?.tvUpExercise?.visibility=View.VISIBLE
                    binding?.tvUpExercise?.text=exerciseList!![currentExercisePos+1].getName()
                    setupRestView()
                }else {
//                    binding?.ivGif?.visibility=View.VISIBLE
//                    binding?.ivGif?.setImageResource(R.drawable.water_fill)
//                    setupRestView()
                    val intent= Intent(this@ExerciseActivity,MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(
                        this@ExerciseActivity,
                        "30 sec Exercise completed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
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
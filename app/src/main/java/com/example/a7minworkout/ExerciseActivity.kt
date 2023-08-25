package com.example.a7minworkout

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.a7minworkout.databinding.ActivityExerciseBinding
import java.util.Locale


class ExerciseActivity : AppCompatActivity(),TextToSpeech.OnInitListener  {
    private var binding:ActivityExerciseBinding?=null
    private var restTimer:CountDownTimer?=null
    private var restProgress=0
    private var exerciseTimer:CountDownTimer?=null
    private var exerciseProgress=0
    private var exerciseList:ArrayList<ExerciseModel>?=null
    private var currentExercisePos=-1

    private var tts: TextToSpeech? = null
    private lateinit var mediaPlayer: MediaPlayer
    private var soundPlaying = false
//    private var imageView = binding?.ivGif

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolBarExercise)
        mediaPlayer = MediaPlayer.create(this, R.raw.new_countdown)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        exerciseList=Constants.defaultExerciseList()
        tts = TextToSpeech(this, this)
        binding?.toolBarExercise?.setNavigationOnClickListener{
            onBackPressed()
        }
        binding?.lottieAnimationView?.playAnimation()
        setupRestView()

    }
    private fun setupRestView(){
//        binding?.ll1?.visibility=View.VISIBLE
//        binding?.ivGif?.visibility=View.VISIBLE
//        binding?.tvTitle?.visibility=View.VISIBLE
        binding?.tvTitle?.text="GET READY FOR EXERCISE"
        binding?.mainConstraint?.setBackgroundResource(R.drawable.background_img)
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
        speakOut("Let's rest for 10 Seconds now.")
        setRestProgressbar()
    }

    private fun setupExerciseView(){
//        binding?.ll1?.visibility=View.GONE
//        binding?.ivGif?.visibility=View.VISIBLE
//        binding?.tvTitle?.visibility=View.VISIBLE
//        binding?.flProgressBar?.visibility= View.VISIBLE
        binding?.mainConstraint?.setBackgroundResource(R.drawable._459163)
        binding?.progressBar?.max=30
        binding?.tvTimer?.text=30.toString()

        binding?.tvUpExercise?.visibility=View.GONE
        binding?.tvTitle?.text=exerciseList!![currentExercisePos].getName()
//        binding?.flExerciseBar?.visibility= View.VISIBLE

//        binding?.ll2?.visibility=View.VISIBLE
//        binding?.ivGifExercise?.visibility=View.VISIBLE
//        binding?.lottieAnimationView?.visibility=View.VISIBLE
        binding?.lottieAnimationView?.setAnimation(exerciseList!![currentExercisePos].getImage())
        binding?.lottieAnimationView?.playAnimation()
//        binding?.ivGif?.visibility=View.VISIBLE
//        binding?.ivGif?.setImageResource(exerciseList!![currentExercisePos].getImage())

//        val layoutParams = binding?.tvTitle?.layoutParams as ConstraintLayout.LayoutParams
//        layoutParams.bottomToTop = binding?.flExerciseBar?.id!!
//        binding?.tvTitle?.layoutParams=layoutParams
//        binding?.tvTitle?.requestLayout()

        if(exerciseTimer!=null){
            exerciseTimer?.cancel()
            exerciseProgress=0
        }
        speakOut(binding?.tvTitle?.text.toString())
        setExerciseProgressbar()
    }

    private fun setRestProgressbar(){

        binding?.progressBar?.progress=restProgress
        restTimer=object:CountDownTimer(11000,1100){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress=11-restProgress
                binding?.tvTimer?.text=(11-restProgress).toString()
                if(!soundPlaying){
                    playSound()
                }
            }

            override fun onFinish() {
                stopSound()
                currentExercisePos++
                setupExerciseView()
                Toast.makeText(this@ExerciseActivity, "Lets start the Exercise.", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported")
            }
        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }
    private fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    private fun stopSound() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
        }
    }

    private fun playSound() {
        mediaPlayer.start()
    }

    private fun setExerciseProgressbar(){
        binding?.progressBar?.progress=exerciseProgress
        exerciseTimer=object:CountDownTimer(30000,1000){
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding?.progressBar?.progress=30-exerciseProgress
                binding?.tvTimer?.text=(30-exerciseProgress).toString()
            }

            override fun onFinish() {
                if(currentExercisePos < exerciseList?.size!! - 1){
//                    binding?.ivGif?.visibility=View.GONE
//                    Picasso.get().load(R.drawable.break_animation).into(imageView)
//                    binding?.ivGif?.setImageResource(R.drawable.animation_break)

//                    binding?.lottieAnimationView?.visibility=View.VISIBLE
                    binding?.lottieAnimationView?.setAnimation("break_animation.json")
                    binding?.lottieAnimationView?.playAnimation()
                    binding?.tvUpExercise?.visibility=View.VISIBLE
                    binding?.tvUpExercise?.text="Upcoming Exercise\n"+exerciseList!![currentExercisePos+1].getName()
                    setupRestView()
                }else {
//                    binding?.ivGif?.visibility=View.VISIBLE
//                    binding?.ivGif?.setImageResource(R.drawable.water_fill)
//                    setupRestView()
                    val intent= Intent(this@ExerciseActivity,MainActivity::class.java)
                    startActivity(intent)
                    speakOut("Congratulation, Exercise completed")
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
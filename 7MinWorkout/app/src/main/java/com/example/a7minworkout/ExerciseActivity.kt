package com.example.a7minworkout

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minworkout.databinding.ActivityExerciseBinding
import com.example.a7minworkout.databinding.DialogCustomBackConfirmationBinding
import kotlinx.coroutines.delay
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
    private var exerciseTimerDuration:Long = 30
    private var restTimerDuration:Long = 10
    private var exerciseAdapter: ExerciseStatusAdapter? = null

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
            customDialogForBackButton()
        }
        binding?.lottieAnimationView?.playAnimation()
        startCountdownAfterDelay(10)
        setupRestView()
        setupExerciseStatusRecyclerView()
    }
    private fun startCountdownAfterDelay(delaySeconds: Int) {
        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            // Start the countdown timer
            startCountdownTimer(7)
        }, (delaySeconds * 1000).toLong())
    }


    private fun startCountdownTimer(minutes: Int) {
        // Replace this with your countdown timer logic
        // Here, we'll update the TextView every second for simplicity
        var secondsRemaining = minutes * 60

        val countdownRunnable = object : Runnable {
            override fun run() {
                val minutes = secondsRemaining / 60
                val seconds = secondsRemaining % 60

//                binding?.countdownTimer?.text = "Countdown: ${String.format("%02d:%02d", minutes, seconds)}"
                binding?.toolBarExercise?.title = "Countdown: ${String.format("%02d:%02d", minutes, seconds)}"

                if (secondsRemaining > 0) {
                    secondsRemaining--
//                    binding?.countdownTimer?.postDelayed(this, 1000)
                    binding?.toolBarExercise?.postDelayed(this,1050)
                } else {
//                    binding?.countdownTimer?.text = "Countdown: 00:00"
                    binding?.toolBarExercise?.title = "Countdown: 00:00"
                }
            }
        }
        binding?.toolBarExercise?.post(countdownRunnable)
    }

    private fun customDialogForBackButton() {
        val customDialog = Dialog(this)
        val dialogBinding = DialogCustomBackConfirmationBinding.inflate(layoutInflater)

        customDialog.setContentView(dialogBinding.root)

        customDialog.setCanceledOnTouchOutside(false)
        dialogBinding.tvYes.setOnClickListener {
            this@ExerciseActivity.finish()
            customDialog.dismiss() // Dialog will be dismissed
        }
        dialogBinding.tvNo.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }

    private fun setupRestView(){

        binding?.tvTitle?.text="GET READY FOR EXERCISE"
        binding?.mainConstraint?.setBackgroundResource(R.drawable.background_img)

        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }
        speakOut("Let's rest for 10 Seconds now.")
        setRestProgressbar()
    }
    private fun setupExerciseView(){

        binding?.mainConstraint?.setBackgroundResource(R.drawable._459163)
        binding?.progressBar?.max=31
        binding?.tvTimer?.text=30.toString()

        binding?.tvUpExercise?.visibility=View.GONE
        binding?.tvTitle?.text=exerciseList!![currentExercisePos].getName()

        binding?.lottieAnimationView?.setAnimation(exerciseList!![currentExercisePos].getImage())
        binding?.lottieAnimationView?.playAnimation()

        if(exerciseTimer!=null){
            exerciseTimer?.cancel()
            exerciseProgress=0
        }
        speakOut(binding?.tvTitle?.text.toString())
        setExerciseProgressbar()
    }
    private fun setRestProgressbar(){

        binding?.progressBar?.progress=restProgress
        restTimer=object:CountDownTimer(restTimerDuration*1000,1050){
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
                exerciseList!![currentExercisePos].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    setupExerciseView()
                }, 1050)
//                Toast.makeText(this@ExerciseActivity, "Lets start the Exercise.", Toast.LENGTH_SHORT).show()
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
        exerciseTimer=object:CountDownTimer(exerciseTimerDuration*1000,1050){
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding?.progressBar?.progress=30-exerciseProgress
                binding?.tvTimer?.text=(30-exerciseProgress).toString()
            }

            override fun onFinish() {
                if(currentExercisePos < exerciseList?.size!! - 1){
                    exerciseList!![currentExercisePos].setIsSelected(false)
                    exerciseList!![currentExercisePos].setIsCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()
                    val handler = Handler(Looper.getMainLooper())
                    handler.postDelayed({
                        // Put the code you want to execute after the delay here
                        binding?.lottieAnimationView?.setAnimation("break_animation.json")
                        binding?.lottieAnimationView?.playAnimation()
                        binding?.tvUpExercise?.visibility=View.VISIBLE
                        binding?.tvUpExercise?.text="Upcoming Exercise\n"+exerciseList!![currentExercisePos+1].getName()
                        setupRestView()
                    }, 1050)

                }else {
                    finish()
                    val intent = Intent(this@ExerciseActivity,FinishActivity::class.java)
                    startActivity(intent)
                    speakOut("Congratulation, Exercise completed")
//                    Toast.makeText(
//                        this@ExerciseActivity,
//                        "Exercise completed.",
//                        Toast.LENGTH_SHORT
//                    ).show()
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

    override fun onBackPressed() {
        customDialogForBackButton()
    }
    private fun setupExerciseStatusRecyclerView() {

        // Defining a layout manager for the recycle view
        // Here we have used a LinearLayout Manager with horizontal scroll.
        binding?.rvExerciseStatus?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // As the adapter expects the exercises list and context so initialize it passing it.
        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!)

        // Adapter class is attached to recycler view
        binding?.rvExerciseStatus?.adapter = exerciseAdapter
    }
}
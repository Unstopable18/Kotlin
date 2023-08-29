package com.example.firebasesigninauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebasesigninauthentication.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var mUsername:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mUsername=intent.getStringExtra("Message")
        binding.tvMessage.text=mUsername
        firebaseAuth = FirebaseAuth.getInstance()
        binding.button.setOnClickListener{
            firebaseAuth.signOut()
            intent= Intent(this, LoginOptionActivity::class.java)
            startActivity(intent)
        }
    }
}
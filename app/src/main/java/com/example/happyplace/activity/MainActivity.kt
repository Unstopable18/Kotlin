package com.example.happyplace.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.happyplace.adapters.HappyPlaceAdapter
import com.example.happyplace.database.DatabaseHandler
import com.example.happyplace.databinding.ActivityMainBinding
import com.example.happyplace.model.HappyPlaceModel

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.fabAddHP?.setOnClickListener{
            val intent= Intent(this, AddHappyPlaceActivity::class.java)
            startActivity(intent)
        }
        getHappyPlacesListFromLocalDB()

    }

    private fun getHappyPlacesListFromLocalDB() {

        val dbHandler = DatabaseHandler(this)

        val getHappyPlacesList = dbHandler.getHappyPlacesList()

        if (getHappyPlacesList.size > 0) {
            binding?.rvHappyPlacesList?.visibility = View.VISIBLE
            binding?.tvNoRecordsAvailable?.visibility = View.GONE
            setupHappyPlacesRecyclerView(getHappyPlacesList)
        } else {
            binding?.rvHappyPlacesList?.visibility = View.GONE
            binding?.tvNoRecordsAvailable?.visibility = View.VISIBLE
        }
    }

    private fun setupHappyPlacesRecyclerView(happyPlacesList: ArrayList<HappyPlaceModel>) {

        binding?.rvHappyPlacesList?.layoutManager = LinearLayoutManager(this)
        binding?.rvHappyPlacesList?.setHasFixedSize(true)

        val placesAdapter = HappyPlaceAdapter(this, happyPlacesList)
        binding?.rvHappyPlacesList?.adapter = placesAdapter
    }

    // TODO(Step 2: Declare a static variable which we will using for notify the item is added when we will be returning back after adding.)
    // START
    companion object{
        private const val ADD_PLACE_ACTIVITY_REQUEST_CODE = 1
    }
}
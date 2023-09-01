package com.example.happyplace.activity

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.happyplace.databinding.ActivityHappyPlaceDetailBinding
import com.example.happyplace.model.HappyPlaceModel

class HappyPlaceDetailActivity : AppCompatActivity() {
    private var binding:ActivityHappyPlaceDetailBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHappyPlaceDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        var happyPlaceDetailModel: HappyPlaceModel? = null

        if (intent.hasExtra(MainActivity.EXTRA_PLACE_DETAILS)) {
            happyPlaceDetailModel = intent.getSerializableExtra(MainActivity.EXTRA_PLACE_DETAILS) as HappyPlaceModel
        }

        if (happyPlaceDetailModel != null) {

            setSupportActionBar(binding?.toolbarHappyPlaceDetail)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = happyPlaceDetailModel.title

            binding?.toolbarHappyPlaceDetail?.setNavigationOnClickListener {
                onBackPressed()
            }

            binding?.ivPlaceImage?.setImageURI(Uri.parse(happyPlaceDetailModel.image))
            binding?.tvDescription?.text = happyPlaceDetailModel.description
            binding?.tvLocation?.text = happyPlaceDetailModel.location
        }

    }
}
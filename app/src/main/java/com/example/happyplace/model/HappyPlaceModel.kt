package com.example.happyplace.model

import java.io.Serializable

class HappyPlaceModel (
    val id: Int,
    val title: String,
    val image: String,
    val description: String,
    val date: String,
    val location: String,
    val latitude: Double,
    val longitude: Double
    ) : Serializable
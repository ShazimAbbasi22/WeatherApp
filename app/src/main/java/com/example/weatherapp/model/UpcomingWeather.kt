package com.example.weatherapp.model

data class UpcomingWeather(
    val day: String,
    val image: Int,
    val status: String,
    val maxTemp: String,
    val minTemp: String
)

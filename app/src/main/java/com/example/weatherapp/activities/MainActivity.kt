package com.example.weatherapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.adapters.MyAdapter
import com.example.weatherapp.R
import com.example.weatherapp.api.ApiInterface
import com.example.weatherapp.api.RetrofitInstance
import com.example.weatherapp.model.CurrentWeather
import com.example.weatherapp.model.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Step:1 Initialize all the views component of current weather below here
        val currentTemperature = findViewById<TextView>(R.id.currentTemperature)
        val currentWeatherDiscription = findViewById<TextView>(R.id.currentWeatherDiscription)
        val currentWindSpeed = findViewById<TextView>(R.id.currentWindSpeed)
        val currentHumidity = findViewById<TextView>(R.id.currentHumidity)


        // Step:2 Initialize the *retrofit instance in a function along with *city parameter and passes the data
        // to the *views by using the *enqueue function through overriding two members (onResponse & onFailure)
        fun getCurrentWeather(cityName: String) {
            val apiService = RetrofitInstance.retrofit.create(ApiInterface::class.java)
            val call = apiService.getWeather(cityName, "f9fcc3edba668fb47bae670abb317826")

            call.enqueue(object : Callback<CurrentWeather> {
                override fun onResponse(
                    call: Call<CurrentWeather>,
                    response: Response<CurrentWeather>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        val temp = responseBody?.main?.temp?.toInt()
                        currentTemperature.text = "${temp}°"
                        currentWindSpeed.text = "${response.body()?.wind?.speed?.toInt()} km/h"
                        currentHumidity.text = "${responseBody?.main?.humidity.toString()}%"
                        currentWeatherDiscription.text =
                            responseBody?.weather?.firstOrNull()?.description.toString().uppercase()
                    }
                }

                override fun onFailure(call: Call<CurrentWeather>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }


        // Here we set the default city to be displayed at the launch of the application
        val defaultCityWeather = getCurrentWeather("hyderabad")


        // Here we implement the search city feature by using the *setQueryTestListener Function, passing *object of SearchView type as parameter then overriding the members
        val searchCity = findViewById<androidx.appcompat.widget.SearchView>(R.id.searchCity)
        searchCity.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    val myFunc = getCurrentWeather(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })


        val data: ArrayList<WeatherData> = arrayListOf()
        val upcoming = findViewById<TextView>(R.id.upcoming)
        data.add(WeatherData("5 PM", R.drawable.sunny, "32°"))
        data.add(WeatherData("6 PM", R.drawable.cloudy, "32°"))
        data.add(WeatherData("7 PM", R.drawable.windy, "32°"))
        data.add(WeatherData("8 PM", R.drawable.cloudy, "32°"))
        data.add(WeatherData("9 PM", R.drawable.sunny, "32°"))
        data.add(WeatherData("10 PM", R.drawable.storm, "32°"))
        data.add(WeatherData("11 PM", R.drawable.sunny, "32°"))


        upcoming.setOnClickListener {
            val intent = Intent(this, UpcomingWeatherActivity::class.java)
            startActivity(intent)

        }


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = MyAdapter(data)

    }

}

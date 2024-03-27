package com.example.weatherapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.adapters.MyAdapter
import com.example.weatherapp.R
import com.example.weatherapp.model.WeatherData
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
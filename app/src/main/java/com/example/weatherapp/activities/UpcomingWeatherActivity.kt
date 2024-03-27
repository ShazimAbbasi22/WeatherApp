package com.example.weatherapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.adapters.MyAdapter2
import com.example.weatherapp.R
import com.example.weatherapp.model.UpcomingWeather
import java.util.ArrayList

class UpcomingWeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_upcoming_weather)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val data: ArrayList<UpcomingWeather> = arrayListOf()
        val back = findViewById<ImageView>(R.id.imageView5)

        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        data.add(UpcomingWeather("Tue", R.drawable.cloudy, "Cloudy", "37°", "23°"))
        data.add(UpcomingWeather("Wed", R.drawable.storm, "Storm", "39°", "22°"))
        data.add(UpcomingWeather("Thu", R.drawable.windy, "Windy", "35°", "24°"))
        data.add(UpcomingWeather("Fri", R.drawable.sunny, "Mostly Sunny", "36°", "21°"))
        data.add(UpcomingWeather("Sat", R.drawable.cloudy_sunny, "Cloudy Sunny", "40°", "25°"))
        data.add(UpcomingWeather("Sun", R.drawable.cloudy, "Cloudy", "33°", "27°"))

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView2)

        recyclerView.layoutManager =
            LinearLayoutManager(this)
        recyclerView.adapter = MyAdapter2(data)

    }
}
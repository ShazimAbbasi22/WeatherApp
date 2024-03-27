package com.example.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.WeatherData
import java.util.ArrayList

class MyAdapter(private val userData: ArrayList<WeatherData>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = userData[position]
        holder.hourTxt.text = data.hour
        holder.hourWeather.setImageResource(data.image)
        holder.hourTemp.text = data.temperature
    }

    override fun getItemCount(): Int {
        return userData.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var hourTxt = itemView.findViewById<TextView>(R.id.hourText)
        val hourWeather = itemView.findViewById<ImageView>(R.id.hourWeather)
        val hourTemp = itemView.findViewById<TextView>(R.id.hourTemp)
    }
}
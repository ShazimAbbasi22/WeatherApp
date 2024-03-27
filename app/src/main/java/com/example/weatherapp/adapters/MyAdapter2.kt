package com.example.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.UpcomingWeather
import java.util.ArrayList

class MyAdapter2(private val userData: ArrayList<UpcomingWeather>): RecyclerView.Adapter<MyAdapter2.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_layout2, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = userData[position]
        holder.day.text = data.day
        holder.image.setImageResource(data.image)
        holder.status.text = data.status
        holder.maxTemp.text = data.maxTemp
        holder.minTemp.text = data.minTemp
    }

    override fun getItemCount(): Int {
        return userData.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val day = itemView.findViewById<TextView>(R.id.dayTxt)
        val image = itemView.findViewById<ImageView>(R.id.imageView6)
        val status = itemView.findViewById<TextView>(R.id.status)
        val maxTemp = itemView.findViewById<TextView>(R.id.maxTemp)
        val minTemp = itemView.findViewById<TextView>(R.id.lowTemp)

    }
}
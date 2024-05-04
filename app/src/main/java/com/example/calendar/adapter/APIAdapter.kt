package com.example.calendar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.calendar.R
import com.example.calendar.api.HolidayPojo

class APIAdapter(private val context: Context, var HolidayPojo: HolidayPojo, private var imageResources: IntArray) : RecyclerView.Adapter<APIAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val holidaysTextView: TextView = itemView.findViewById(R.id.holidaydata)
        val imageView: ImageView = itemView.findViewById(R.id.view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the layout for each item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_row, parent, false)
        return ViewHolder(view)
    }



//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
//        var data= arrayOf(HolidayPojo.get(position).holidays)
//            for (i in data.indices){
//            holder.holidaysTextView.text= data[i].toString()
//            }
//
//           holder.imageView.setImageResource(imageResources[position])
//    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Access the holidays array for the current position
        val holidays = HolidayPojo.get(position).holidays

        // Join the holidays array elements with a separator (e.g., ", ") and set the result as the text for the TextView
        holder.holidaysTextView.text = holidays.joinToString(", ")

        // Set the image resource for the current position
        holder.imageView.setImageResource(imageResources[position])
    }


    override fun getItemCount(): Int {

        return HolidayPojo.size
    }



}


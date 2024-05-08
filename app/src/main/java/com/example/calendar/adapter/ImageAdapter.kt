package com.example.calendar.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.calendar.R
import java.util.Calendar
import java.util.Locale


class ImageAdapter(private  val imageList: ArrayList<Int>, private val viewPager2: ViewPager2) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val calendarView: CalendarView = itemView.findViewById(R.id.calendarView)
        val monthTextView: TextView = itemView.findViewById(R.id.monthTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_seasonal_image, parent, false)
        return ImageViewHolder(view)
    }


    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        // Set the image resource based on the current position
        holder.imageView.setImageResource(imageList[position])

        // Calculate the date and month based on the current position
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, position)

        // Set the CalendarView date to the first day of the current month
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        holder.calendarView.date = calendar.timeInMillis

        // Set the month name in the TextView
        val monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
        holder.monthTextView.text = monthName

        // Handle the end of the list scenario
        if (position == imageList.size - 1) {
            viewPager2.post(runnable)
        }
    }






    override fun getItemCount(): Int {
        return imageList.size
    }

    private val runnable = Runnable {
        imageList.addAll(imageList)
        notifyDataSetChanged()
    }

}





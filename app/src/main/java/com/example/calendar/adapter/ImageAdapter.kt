package com.example.calendar.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.calendar.R
import java.util.Calendar


class ImageAdapter(private  val imageList: ArrayList<Int>, private val viewPager2: ViewPager2 ) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val calendarView: CalendarView = itemView.findViewById(R.id.calendarView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_seasonal_image, parent, false)
        return ImageViewHolder(view)
    }


    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.imageView.setImageResource(imageList[position])

        if (position == imageList.size - 1) {
            viewPager2.post(runnable)
        }

        setCalendarMonth(holder.calendarView, position)

    }

    private fun setCalendarMonth(calendarView: CalendarView, position: Int) {
        // Adjust the calendar view's date based on the position.
        // This assumes you have some mapping between positions and months.
        val calendar = Calendar.getInstance()
        // This example sets the month based on position, assuming the current year.
        calendar.set(Calendar.MONTH, position)
        calendarView.date = calendar.timeInMillis
    }


    override fun getItemCount(): Int {
        return imageList.size
    }

    private val runnable = Runnable {
        imageList.addAll(imageList)
        notifyDataSetChanged()
    }

}



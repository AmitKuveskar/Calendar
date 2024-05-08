package com.example.calendar.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.calendar.R
import com.example.calendar.api.MuhuratPojo

class APIAdapter2 (private val context: Context, private val MuhuratPojo: MuhuratPojo, private var imageResources: IntArray) : RecyclerView.Adapter<APIAdapter2.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val MuhuratTextView: TextView = itemView.findViewById(R.id.muhuratdata)
        val CategoryTextView: TextView = itemView.findViewById(R.id.category)
        val imageView: ImageView = itemView.findViewById(R.id.view2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_row2, parent, false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val muhuratData = MuhuratPojo[position]

        // Set the text for Muhurat and category
        holder.MuhuratTextView.text = muhuratData.muhurat.joinToString(separator = ", ")
        holder.CategoryTextView.text = muhuratData.category

        // Determine the correct image resource based on the category
        when (muhuratData.category) {
            "Marriage" -> holder.imageView.setImageResource(imageResources[0])
            "VehicleP" -> holder.imageView.setImageResource(imageResources[1])
            "Namkaran" -> holder.imageView.setImageResource(imageResources[2])
            "Grih" -> holder.imageView.setImageResource(imageResources[3])
            else -> holder.imageView.setImageResource(R.drawable.namkaran) // Default image if category not matched
        }
    }

    override fun getItemCount(): Int {

        return MuhuratPojo.size
    }



}
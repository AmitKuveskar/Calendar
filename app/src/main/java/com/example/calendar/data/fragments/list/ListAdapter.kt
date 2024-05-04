package com.example.calendar.data.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.calendar.R
import com.example.calendar.data.User

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        val id: TextView = itemView.findViewById(R.id.id)
        val gym: TextView = itemView.findViewById(R.id.gym)
        val colorview: View = itemView.findViewById(R.id.color)
        val starttime: TextView = itemView.findViewById(R.id.StartTime)
        val endtime: TextView = itemView.findViewById(R.id.EndTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
//        holder.id.text= currentItem.id.toString()
       holder.gym.text  = currentItem.Note
        holder.colorview.setBackgroundColor(currentItem.color)
        holder.starttime.text = currentItem.startTime
        holder.endtime.text= currentItem.endTime

    }
    override fun getItemCount(): Int {
        return userList.size
    }


    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }

}
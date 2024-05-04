package com.example.calendar.api

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calendar.Dashboard
import com.example.calendar.R
import com.example.calendar.adapter.APIAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HolidayActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var backButton: ImageButton
    private lateinit var btnSelectMonth: TextView
    private lateinit var selectMonthTextView: TextView
    private lateinit var images: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_holiday)

        // Initialize views
        recyclerView = findViewById(R.id.rv)
        backButton = findViewById(R.id.Back)
        btnSelectMonth = findViewById(R.id.selectmonth)
        selectMonthTextView = findViewById(R.id.selectmonth)

        // Define images array
        images = intArrayOf(
            R.drawable.january,
            R.drawable.february,
            R.drawable.march,
            R.drawable.april,
            R.drawable.may,
            R.drawable.june,
            R.drawable.july,
            R.drawable.august,
            R.drawable.september,
            R.drawable.october,
            R.drawable.november,
            R.drawable.december
        )





        // Handle back button click
        backButton.setOnClickListener {
            val intent = Intent(this@HolidayActivity, Dashboard::class.java)
            startActivity(intent)
        }

        // Handle select month button click
        btnSelectMonth.setOnClickListener {


        }

        getData()
    }


    private fun getData() {
        RetrofitInstance.apiInterface.getData().enqueue(object : Callback<HolidayPojo?> {

            override fun onResponse(call: Call<HolidayPojo?>, response: Response<HolidayPojo?>) {
                if (response.code()==200 && response.body()!= null){

                    val LinearLayoutManager = LinearLayoutManager(this@HolidayActivity)
                    recyclerView.layoutManager= LinearLayoutManager

                    val recylerViewAdapter = APIAdapter(this@HolidayActivity,response.body()!!,images)
                    recyclerView.adapter = recylerViewAdapter

                    Toast.makeText(this@HolidayActivity, "success", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@HolidayActivity, "error", Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<HolidayPojo?>, t: Throwable) {
                Toast.makeText(this@HolidayActivity, "" + t.message, Toast.LENGTH_SHORT)
                    .show()
            }
        })
}
}
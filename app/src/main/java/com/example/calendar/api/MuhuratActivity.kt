package com.example.calendar.api

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calendar.Dashboard
import com.example.calendar.R
import com.example.calendar.adapter.APIAdapter2
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MuhuratActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    private lateinit var backButton: ImageButton
    private lateinit var images: IntArray
    private lateinit var monthTabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_muhurat)

        recyclerView = findViewById(R.id.rv2)
        backButton = findViewById(R.id.Back2)
        monthTabLayout = findViewById(R.id.monthTabLayout)

        // Define images array
        images = intArrayOf(
            R.drawable.marriage,
            R.drawable.vehiclep,
            R.drawable.namkaran,
            R.drawable.grih
        )

        // Set up back button click listener
        backButton.setOnClickListener {
            startActivity(Intent(this@MuhuratActivity, Dashboard::class.java))
        }

        // Set up the month tab layout
        setupMonthTabs()

        // Initially load data for January
        fetchDataForMonth("January")
    }

    private fun setupMonthTabs() {
        // List of months for the tabs
        val months = arrayOf(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        )

        // Add tabs for each month
        for (month in months) {
            monthTabLayout.addTab(monthTabLayout.newTab().setText(month))
        }

        // Handle month tab selection changes
        monthTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // Fetch data for the selected month
                val selectedMonth = tab.text.toString()
                fetchDataForMonth(selectedMonth)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // Do nothing when a tab is unselected
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // Do nothing when a tab is reselected
            }
        })
    }

    private fun fetchDataForMonth(month: String) {
        // Fetch data for the selected month from the API
        RetrofitInstance.apiInterface.getMuhuratlist(month).enqueue(object : Callback<MuhuratPojo?> {
            override fun onResponse(call: Call<MuhuratPojo?>, response: Response<MuhuratPojo?>) {
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()
                    // Set up RecyclerView with the new data
                    recyclerView.layoutManager = LinearLayoutManager(this@MuhuratActivity)
                    recyclerView.adapter = APIAdapter2(this@MuhuratActivity, data!!, images)
                } else {
                    Toast.makeText(this@MuhuratActivity, "Failed to load data for $month", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MuhuratPojo?>, t: Throwable) {
                Toast.makeText(this@MuhuratActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

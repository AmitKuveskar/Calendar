package com.example.calendar

import BackgroundToForegroundTransformer

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.calendar.adapter.ImageAdapter
import com.example.calendar.api.HolidayActivity

import com.example.calendar.api.MuhuratActivity
import com.example.calendar.data.AppNoteActivity
import java.util.Calendar


class Dashboard : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var adapter: ImageAdapter
    private lateinit var calendarView: CalendarView
    private  lateinit var MuhuratBtn:Button
    private lateinit var HolidayBtn:Button
    private lateinit var AddNoteBtn:Button
    lateinit var roomdb : Button
    private lateinit var ShareApp: Button


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        viewPager2 = findViewById(R.id.viewPager2)
        calendarView = findViewById(R.id.calendarView)
        MuhuratBtn = findViewById(R.id.Muhurat)
        HolidayBtn = findViewById(R.id.Holiday)
        AddNoteBtn = findViewById(R.id.AppNote)
        roomdb = findViewById(R.id.AppNote)
        ShareApp = findViewById(R.id.ShareApp)




        init()
        setUpTransformer()


        MuhuratBtn.setOnClickListener {
            val intent = Intent(this@Dashboard, MuhuratActivity::class.java)
            startActivity(intent)
        }

        HolidayBtn.setOnClickListener {
            val intent = Intent(this@Dashboard, HolidayActivity::class.java)
            startActivity(intent)
        }


        // Set listener for calendarView to detect month change
        calendarView.setOnDateChangeListener { _, year, month, _ ->
            updateImagesForMonth(month)
            adapter.notifyDataSetChanged()
        }

        roomdb.setOnClickListener {
            val intent = Intent (this@Dashboard, AppNoteActivity::class.java)
            startActivity(intent)
        }

        ShareApp.setOnClickListener{
            shareApp()

        }

    }

    private fun shareApp() {
        // Create a share Intent
        val shareIntent = Intent().apply {
            // Set the action to share
            action = Intent.ACTION_SEND
            // Set the type of data to share
            type = "text/plain"
            // Set the text to share
            putExtra(Intent.EXTRA_TEXT, "Check out this awesome app: [Your App Link]")
        }

        // Use the Intent to show the share dialog
        val chooser = Intent.createChooser(shareIntent, "Share App")
        startActivity(chooser)
    }


    private fun setUpTransformer() {
        val transformer = BackgroundToForegroundTransformer()
        viewPager2.setPageTransformer(transformer)
    }




    private fun init() {
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()

        // Initialize imageList with default images or images for the current month
        updateImagesForMonth(Calendar.getInstance().get(Calendar.MONTH))

        adapter = ImageAdapter(imageList, viewPager2)
        viewPager2.adapter = adapter

        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        viewPager2.setCurrentItem(1, false)
    }



    private fun updateImagesForMonth(month: Int) {
        // Clear previous images and add images for the selected month and adjacent months
        imageList.clear()

        // Get images for the selected month
        val selectedMonthImages = getImageForMonth(month)

        // Add images to the imageList
        when (month) {
            Calendar.JANUARY -> {
                imageList.add(selectedMonthImages[0])  // Summer card as main card
                imageList.add(selectedMonthImages[2])  // Winter card as left card
                imageList.add(selectedMonthImages[1])  // Monsoon card as right card
            }
            Calendar.FEBRUARY -> {
                imageList.add(selectedMonthImages[1])  // Summer card as main card
                imageList.add(selectedMonthImages[0])  // Winter card as left card
                imageList.add(selectedMonthImages[2])  // Monsoon card as right card
            }
            Calendar.MARCH -> {
                imageList.add(selectedMonthImages[1])  // Winter card as main card
                imageList.add(selectedMonthImages[0])  // Summer card as left card
                imageList.add(selectedMonthImages[2])  // Monsoon card as right card
            }
            Calendar.APRIL -> {
                imageList.add(selectedMonthImages[1])  // Monsoon card as main card
                imageList.add(selectedMonthImages[0])  // Summer card as left card
                imageList.add(selectedMonthImages[2])  // Winter card as right card
            }
            Calendar.MAY -> {
                imageList.add(selectedMonthImages[1])  // Summer card as main card
                imageList.add(selectedMonthImages[0])  // Monsoon card as left card
                imageList.add(selectedMonthImages[2])  // Winter card as right card
            }
            Calendar.JUNE -> {
                imageList.add(selectedMonthImages[0])  // Summer card as main card
                imageList.add(selectedMonthImages[1])  // Winter card as left card
                imageList.add(selectedMonthImages[2])  // Monsoon card as right card
            }
            Calendar.JULY -> {
                imageList.add(selectedMonthImages[0])  // Winter card as main card
                imageList.add(selectedMonthImages[1])  // Summer card as left card
                imageList.add(selectedMonthImages[2])  // Monsoon card as right card
            }
            Calendar.AUGUST -> {
                imageList.add(selectedMonthImages[0])  // Monsoon card as main card
                imageList.add(selectedMonthImages[1])  // Summer card as left card
                imageList.add(selectedMonthImages[2])  // Winter card as right card
            }
            Calendar.SEPTEMBER -> {
                imageList.add(selectedMonthImages[0])  // Summer card as main card
                imageList.add(selectedMonthImages[1])  // Monsoon card as left card
                imageList.add(selectedMonthImages[2])  // Winter card as right card
            }
            Calendar.OCTOBER -> {
                imageList.add(selectedMonthImages[0])  // Summer card as main card
                imageList.add(selectedMonthImages[2])  // Winter card as left card
                imageList.add(selectedMonthImages[1])  // Monsoon card as right card
            }
            Calendar.NOVEMBER -> {
                imageList.add(selectedMonthImages[0])  // Winter card as main card
                imageList.add(selectedMonthImages[2])  // Summer card as left card
                imageList.add(selectedMonthImages[1])  // Monsoon card as right card
            }
            Calendar.DECEMBER -> {
                imageList.add(selectedMonthImages[0])  // Monsoon card as main card
                imageList.add(selectedMonthImages[2])  // Summer card as left card
                imageList.add(selectedMonthImages[1])  // Winter card as right card
            }
        }
    }




    // Helper function to get image resource ID based on month
    private fun getImageForMonth(month: Int): List<Int> {
        // Adjust month to handle wrap-around (January -> December, December -> January)
        val adjustedMonth = (month + 12) % 12
        // Return list of image resource IDs based on the adjusted month
        return when (adjustedMonth) {
            Calendar.JANUARY -> listOf(R.drawable.summer, R.drawable.monsoon, R.drawable.winter)
            Calendar.FEBRUARY -> listOf(R.drawable.summer, R.drawable.monsoon, R.drawable.winter)
            Calendar.MARCH -> listOf(R.drawable.summer, R.drawable.monsoon, R.drawable.winter)
            Calendar.APRIL -> listOf(R.drawable.summer, R.drawable.monsoon, R.drawable.winter)
            Calendar.MAY -> listOf(R.drawable.summer, R.drawable.monsoon, R.drawable.winter)
            Calendar.JUNE -> listOf(R.drawable.summer, R.drawable.monsoon, R.drawable.winter)
            Calendar.JULY -> listOf(R.drawable.summer, R.drawable.monsoon, R.drawable.winter)
            Calendar.AUGUST -> listOf(R.drawable.monsoon, R.drawable.winter, R.drawable.summer)
            Calendar.SEPTEMBER -> listOf(R.drawable.summer, R.drawable.monsoon, R.drawable.winter)
            Calendar.OCTOBER -> listOf(R.drawable.summer, R.drawable.monsoon, R.drawable.winter)
            Calendar.NOVEMBER -> listOf(R.drawable.summer, R.drawable.monsoon, R.drawable.winter)
            Calendar.DECEMBER -> listOf(R.drawable.summer, R.drawable.monsoon, R.drawable.winter)
            // Add cases for other months
            else -> listOf(R.drawable.monsoon, R.drawable.summer, R.drawable.winter) // Provide a default image if needed
        }


    }

}




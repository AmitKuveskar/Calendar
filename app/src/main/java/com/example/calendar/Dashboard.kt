package com.example.calendar

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.calendar.adapter.ImageAdapter
import com.example.calendar.api.HolidayActivity
import com.example.calendar.api.MuhuratActivity
import com.example.calendar.data.AppNoteActivity


class Dashboard : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var adapter: ImageAdapter
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
        val transformer = DepthPageTransformer()
        viewPager2.setPageTransformer(transformer)
    }




    private fun init() {
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()

        imageList.add(R.drawable.winter)
        imageList.add(R.drawable.summer)
        imageList.add(R.drawable.monsoon)

        // Initialize and set the adapter
        adapter = ImageAdapter(imageList, viewPager2)
        viewPager2.adapter = adapter

        // Optional: Set some additional properties for `ViewPager2`
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        // Set the initial page for `ViewPager2`
            viewPager2.setCurrentItem(2, true)


    }

}




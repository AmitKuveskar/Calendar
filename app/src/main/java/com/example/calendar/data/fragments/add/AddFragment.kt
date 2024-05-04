package com.example.calendar.data.fragments.add

import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.calendar.R
import com.example.calendar.data.User
import com.example.calendar.data.UserViewModel
import java.util.Calendar


class AddFragment : Fragment() {

    lateinit var Note: EditText
    private lateinit var  mUserViewModel: UserViewModel
    private var selectedColor: Int = Color.RED


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        val Insert = view.findViewById<Button>(R.id.Insert)
        Note = view.findViewById(R.id.NoteTxt)



        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val cancel = view.findViewById<Button>(R.id.cancel)
       cancel.setOnClickListener {
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }


        Insert.setOnClickListener{
            insertDataToDatabase()
        }
        view.findViewById<View>(R.id.circleColor1).setOnClickListener {
            selectedColor = Color.RED

        }
        view.findViewById<View>(R.id.circleColor2).setOnClickListener {
            selectedColor = Color.GREEN
        }
        view.findViewById<View>(R.id.circleColor3).setOnClickListener {
            selectedColor = Color.BLUE
        }
        view.findViewById<View>(R.id.circleColor4).setOnClickListener {
            selectedColor = Color.rgb(255, 187, 51)
        }
        view.findViewById<View>(R.id.circleColor5).setOnClickListener {
            selectedColor = Color.rgb(255, 165, 0) // Orange color
        }

        view.findViewById<TextView>(R.id.selecttimestart).setOnClickListener {
            showTimePickerDialog(it as TextView)
        }

        view.findViewById<TextView>(R.id.selecttimeend).setOnClickListener {
            showTimePickerDialog(it as TextView)
        }


        return  view

    }

    private fun showTimePickerDialog(textView: TextView) {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(requireContext(),
            { _, selectedHour, selectedMinute ->
                // Convert the selected time to a formatted string (e.g., "11:00 AM")
                val timeInAmPm = convertToAmPm(selectedHour, selectedMinute)
                textView.text = timeInAmPm
            },
            hour, minute, false // Use a 12-hour format
        )

        timePickerDialog.show()
    }

    private fun convertToAmPm(hour: Int, minute: Int): String {
        val amPm = if (hour >= 12) "PM" else "AM"
        val formattedHour = if (hour > 12) hour - 12 else if (hour == 0) 12 else hour
        return String.format("%02d:%02d %s", formattedHour, minute, amPm)
    }

    private fun insertDataToDatabase() {
        val noteText = Note.text.toString()
        val startTimeText = view?.findViewById<TextView>(R.id.selecttimestart)?.text.toString()
        val endTimeText = view?.findViewById<TextView>(R.id.selecttimeend)?.text.toString()

        // Check input fields for validity
        if (inputCheck(noteText, startTimeText, endTimeText)) {
            // Create a User object with the new data
            val user = User(
                id = 0, // ID will be auto-generated
                Note = noteText,
                color = selectedColor,
                startTime = startTimeText,
                endTime = endTimeText
            )

            // Add the user to the database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully Added", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(Note: String, startTimeText: String, endTimeText: String): Boolean{
        return !(TextUtils.isEmpty(Note))
    }

}
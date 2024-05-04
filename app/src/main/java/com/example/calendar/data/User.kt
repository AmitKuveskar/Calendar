package com.example.calendar.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val Note: String,
    val color: Int,
    val startTime: String,
    val endTime: String

)
package com.example.calendar.api

data class MuhuratPojoItem(
    val category: String,
    val id: String,
    val month: String,
    val muhurat: List<String>
)
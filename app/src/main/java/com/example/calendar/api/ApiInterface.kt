package com.example.calendar.api


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/v3/calendar_2024/holiday_list")
    fun getData(): Call<HolidayPojo>

    @GET("v3/calendar_2024/muhurat_list")
    fun getMuhuratlist(@Query("month") month: String): Call<MuhuratPojo>


}


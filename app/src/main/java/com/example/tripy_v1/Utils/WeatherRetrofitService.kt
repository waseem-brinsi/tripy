package com.example.tripy_v1.Utils

import com.example.tripy_v1.Models.User
import com.example.tripy_v1.Models.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRetrofitService {

    //https://api.openweathermap.org/data/2.5/weather?lat=36.80&lon=10.1815&appid=ced230492360320abde50c50739ea98c
    @GET("weather")
    fun GetWeather(
        @Query(value = "lat") lat:String,
        @Query(value = "lon") lon:String,
        @Query(value = "appid") appid:String
    ): Call<WeatherResponse>

}
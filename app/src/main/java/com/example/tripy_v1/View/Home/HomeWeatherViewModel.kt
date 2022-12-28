package com.example.tripy_v1.View.Home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tripy_v1.Models.Login
import com.example.tripy_v1.Models.LoginResponse
import com.example.tripy_v1.Models.WeatherResponse

import com.example.tripy_v1.Utils.WeatherRetrofitInstance
import com.example.tripy_v1.Utils.WeatherRetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeWeatherViewModel : ViewModel() {


    var WeatherLiveData = MutableLiveData<WeatherResponse>()
    val retrofit = WeatherRetrofitInstance.getRetrofit().create(WeatherRetrofitService::class.java)


    fun GetWeather (lat:String,lon:String,appid:String){
        var res = retrofit.GetWeather(lat,lon,appid)
        res.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                WeatherLiveData.postValue(response.body())
            }
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("resault",t.message.toString())
            }
        })
    }

}
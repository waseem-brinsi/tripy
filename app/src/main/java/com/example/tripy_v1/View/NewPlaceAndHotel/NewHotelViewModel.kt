package com.example.tripy_v1.View.NewPlaceAndHotel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tripy_v1.Models.*
import com.example.tripy_v1.Utils.NodejsRetroService
import com.example.tripy_v1.Utils.NodejsRetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewHotelViewModel:ViewModel() {

    var NewHotelLiveData = MutableLiveData<HotelResponse>()
    val retrofit = NodejsRetrofitInstance.getRetrofit()
    val retService : NodejsRetroService = retrofit.create(NodejsRetroService::class.java)

    fun AddNewHotel(newhotel: Hotel){
        var posted =  retService.createhotel(newhotel)
        posted.enqueue(object : Callback<HotelResponse> {
            override fun onResponse(call: Call<HotelResponse>, response: Response<HotelResponse>) {
                if(response.isSuccessful){
                    NewHotelLiveData.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<HotelResponse>, t: Throwable) {
                Log.d("resault",t.message.toString())
            }
        })
    }




}

package com.example.tripy_v1.View.NewPlaceAndHotel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tripy_v1.Models.Place
import com.example.tripy_v1.Models.PlaceResponse
import com.example.tripy_v1.Utils.NodejsRetroService
import com.example.tripy_v1.Utils.NodejsRetrofitInstance
import com.example.tripy_v1.Models.User
import com.example.tripy_v1.Models.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewPlaceViewModel:ViewModel() {

    var NewPlaceLiveData = MutableLiveData<PlaceResponse>()
    val retrofit = NodejsRetrofitInstance.getRetrofit()
    val retService : NodejsRetroService = retrofit.create(NodejsRetroService::class.java)

    fun AddNewPlace(newplace: Place){
        var posted =  retService.createplace(newplace)
        posted.enqueue(object : Callback<PlaceResponse> {
            override fun onResponse(call: Call<PlaceResponse>, response: Response<PlaceResponse>) {
                if(response.isSuccessful){
                    NewPlaceLiveData.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<PlaceResponse>, t: Throwable) {
                Log.d("resault",t.message.toString())
            }
        })
    }




}

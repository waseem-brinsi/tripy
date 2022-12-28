package com.example.tripy_v1.View.Home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tripy_v1.Models.Place
import com.example.tripy_v1.Utils.NodejsRetroService
import com.example.tripy_v1.Utils.NodejsRetrofitInstance
import com.example.tripy_v1.Models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class HomeViewModel : ViewModel() {
    var PlacesLiveData  = MutableLiveData<MutableList<Place>>()
    val retrofit = NodejsRetrofitInstance.getRetrofit().create(NodejsRetroService::class.java)
    //val Token = "name eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYzOTdjNGRkMDkxYjlkNDVkMGE1ZTQ0MyIsImlhdCI6MTY3MTQwMDg0MCwiZXhwIjoxNjc5MTc2ODQwfQ.zAMf94t_TpM8Zgke-ouJgI367N4arg1pYqS-hL5H0uE"

    fun getPlacesList(Token:String){
        var resault = retrofit.getallplaces(Token)
        resault.enqueue(object : Callback<List<Place>> {
            override fun onResponse(call: Call<List<Place>>, response: Response<List<Place>>) {
                println("===============resault get================")
                if (response.isSuccessful) {
                    PlacesLiveData.postValue(response.body() as MutableList<Place>)
                    Log.d("resault", response.body().toString())
                    Log.d("resault", PlacesLiveData.toString())
                }
            }
            override fun onFailure(call: Call<List<Place>>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }

    fun GetPlaceByName(name : String,Token:String){
        var resault = retrofit.searchPlace(name,Token)
        resault.enqueue(object : Callback<List<Place>>{
            override fun onResponse(call: Call<List<Place>>, response: Response<List<Place>>) {
                if (response.isSuccessful){
                    Log.d("resault",response.body().toString())
                    PlacesLiveData.postValue(response.body() as MutableList<Place>?)
                }
            }
            override fun onFailure(call: Call<List<Place>>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }
}
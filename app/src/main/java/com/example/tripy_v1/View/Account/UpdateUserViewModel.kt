package com.example.tripy_v1.View.Account

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tripy_v1.Models.*
import com.example.tripy_v1.Utils.NodejsRetroService
import com.example.tripy_v1.Utils.NodejsRetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateUserViewModel:ViewModel() {

    var UpdateUserLiveData = MutableLiveData<List<User>>()
    val retrofit = NodejsRetrofitInstance.getRetrofit()
    val retService : NodejsRetroService = retrofit.create(NodejsRetroService::class.java)

    fun searchUser(email: String){
        var posted =  retService.searchUser(email)
        posted.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if(response.isSuccessful){
                    UpdateUserLiveData.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("resault",t.message.toString())
            }
        })
    }




}

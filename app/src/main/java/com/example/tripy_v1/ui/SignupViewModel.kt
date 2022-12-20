package com.example.tripy_v1.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tripy_v1.RetroService
import com.example.tripy_v1.RetrofitInstance
import com.example.tripy_v1.models.User
import com.example.tripy_v1.models.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel:ViewModel() {

    var SignupLiveData = MutableLiveData<UserResponse>()
    val retrofit = RetrofitInstance.getRetrofit()
    val retService : RetroService = retrofit.create(RetroService::class.java)

    fun signup(user: User){
        var posted =  retService.createUser(user)
        posted.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if(response.isSuccessful){
                    SignupLiveData.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("resault",t.message.toString())
            }
        })
    }
}

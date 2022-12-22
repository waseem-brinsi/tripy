package com.example.tripy_v1.ui.Login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tripy_v1.RetroService
import com.example.tripy_v1.RetrofitInstance
import com.example.tripy_v1.models.Login
import com.example.tripy_v1.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel :ViewModel() {

    var LoginLiveData = MutableLiveData<LoginResponse>()
    val retrofit = RetrofitInstance.getRetrofit().create(RetroService::class.java)


    fun logging (LogUser:Login){
        var res = retrofit.loginUser(LogUser)
        res.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                LoginLiveData.postValue(response.body())
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("resault",t.message.toString())
            }
        })
    }

}
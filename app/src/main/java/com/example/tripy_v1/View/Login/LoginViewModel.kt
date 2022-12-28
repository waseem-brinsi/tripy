package com.example.tripy_v1.View.Login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tripy_v1.Utils.NodejsRetroService
import com.example.tripy_v1.Utils.NodejsRetrofitInstance
import com.example.tripy_v1.Models.Login
import com.example.tripy_v1.Models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel :ViewModel() {

    var LoginLiveData = MutableLiveData<LoginResponse>()
    val retrofit = NodejsRetrofitInstance.getRetrofit().create(NodejsRetroService::class.java)


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
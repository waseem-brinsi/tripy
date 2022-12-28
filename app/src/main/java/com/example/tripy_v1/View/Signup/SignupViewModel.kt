package com.example.tripy_v1.View.Signup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tripy_v1.Utils.NodejsRetroService
import com.example.tripy_v1.Utils.NodejsRetrofitInstance
import com.example.tripy_v1.Models.User
import com.example.tripy_v1.Models.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel:ViewModel() {

    var SignupLiveData = MutableLiveData<SignUpResponse>()
    val retrofit = NodejsRetrofitInstance.getRetrofit()
    val retService : NodejsRetroService = retrofit.create(NodejsRetroService::class.java)

    fun signup(user: User){
        var posted =  retService.createUser(user)
        posted.enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                if(response.isSuccessful){
                    SignupLiveData.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                Log.d("resault",t.message.toString())
            }
        })
    }
}

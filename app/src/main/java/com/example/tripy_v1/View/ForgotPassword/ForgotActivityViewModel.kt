package com.example.tripy_v1.View.ForgotPassword

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tripy_v1.Utils.NodejsRetroService
import com.example.tripy_v1.Utils.NodejsRetrofitInstance
import com.example.tripy_v1.Models.ForgotResponse
import com.example.tripy_v1.Models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class ForgotActivityViewModel:ViewModel() {

    var forgotLiveData = MutableLiveData<ForgotResponse>()
    val retrofit = NodejsRetrofitInstance.getRetrofit().create(NodejsRetroService::class.java)

    fun forgetPassword(userEmail:User){
        var res = retrofit.forgetPassword(userEmail)
        res.enqueue(object : Callback<ForgotResponse> {
            override fun onResponse(call: Call<ForgotResponse>, response: Response<ForgotResponse>)
            {
                    forgotLiveData.postValue(response.body())
            }
            override fun onFailure(call: Call<ForgotResponse>, t: Throwable)
            {
                Log.d("resault",t.message.toString())
            }
        })
    }
}
package com.example.tripy_v1.ui.ForgotPassword

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tripy_v1.RetroService
import com.example.tripy_v1.RetrofitInstance
import com.example.tripy_v1.models.ForgotResponse
import com.example.tripy_v1.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class ForgotActivityViewModel:ViewModel() {

    var forgotLiveData = MutableLiveData<ForgotResponse>()
    val retrofit = RetrofitInstance.getRetrofit().create(RetroService::class.java)

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
package com.example.tripy_v1.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tripy_v1.RetroService
import com.example.tripy_v1.RetrofitInstance
import com.example.tripy_v1.models.LoginResponse
import com.example.tripy_v1.models.NewPasswordResponse
import com.example.tripy_v1.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewPasswordViewModel:ViewModel() {
    var NewPasswordActivity = MutableLiveData<NewPasswordResponse>()

    val retrofit = RetrofitInstance.getRetrofit().create(RetroService::class.java)

    fun UpdatePassword(code:String,NewPassword : User){
        var res = retrofit.resetcode(code,NewPassword)
        res.enqueue(object : Callback<NewPasswordResponse> {
            override fun onResponse(call: Call<NewPasswordResponse>, response: Response<NewPasswordResponse>) {
                NewPasswordActivity.postValue(response.body())
            }
            override fun onFailure(call: Call<NewPasswordResponse>, t: Throwable) {
                Log.d("resault",t.message.toString())
            }
        })
    }



}
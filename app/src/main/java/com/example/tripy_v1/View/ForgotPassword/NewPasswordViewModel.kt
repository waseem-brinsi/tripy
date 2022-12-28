package com.example.tripy_v1.View.ForgotPassword

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tripy_v1.Utils.NodejsRetroService
import com.example.tripy_v1.Utils.NodejsRetrofitInstance
import com.example.tripy_v1.Models.NewPasswordResponse
import com.example.tripy_v1.Models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewPasswordViewModel:ViewModel() {
    var NewPasswordActivity = MutableLiveData<NewPasswordResponse>()

    val retrofit = NodejsRetrofitInstance.getRetrofit().create(NodejsRetroService::class.java)

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
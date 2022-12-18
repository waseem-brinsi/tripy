package com.example.tripy_v1.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tripy_v1.RetroService
import com.example.tripy_v1.RetrofitInstance
import com.example.tripy_v1.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class GetallUsersViewModel : ViewModel() {
    var UsersLiveData  = MutableLiveData<MutableList<User>>()
    val retrofit = RetrofitInstance.getRetrofit().create(RetroService::class.java)


    fun getUsersList(){

        var resault = retrofit.getUsersList()
        resault.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                println("===============resault get================")
                if (response.isSuccessful) {
                    UsersLiveData.postValue(response.body() as MutableList<User>)
                    Log.d("resault", response.body().toString())
                    Log.d("resault", UsersLiveData.toString())
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })
    }

    fun GetUserByName(name : String){

        var resault = retrofit.searchUser(name)
        resault.enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful){
                    Log.d("resault",response.body().toString())
                    UsersLiveData.postValue(response.body() as MutableList<User>?)
                }
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("TAG",t.message.toString())
            }
        })

    }


}
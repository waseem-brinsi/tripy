package com.example.tripy_v1.Utils

import com.example.tripy_v1.Models.*
import retrofit2.Call
import retrofit2.http.*


interface NodejsRetroService {

    @GET("users")
    fun getUsersList():Call <List<User>>

    @GET("users")
    fun searchUser(@Query(value = "name") searchText:String):Call<List<User>>

    @POST("users/signup")
    fun createUser(@Body user: User): Call<SignUpResponse>

    @POST("users/login")
    fun loginUser(@Body user: Login): Call<LoginResponse>

    @POST("users/forgotPassword")
    fun forgetPassword(@Body email: User): Call<ForgotResponse>

    @PATCH("users/resetcode/{RC}")
    fun resetcode(@Path("RC") RC:String?, @Body NewPassword :  User?):Call<NewPasswordResponse>

    //get Places
    @GET("places")
    fun getallplaces(@Header("authorization") auth: String):Call <List<Place>>

    @GET("places")
    fun searchPlace(@Query(value = "name") searchText:String,@Header("authorization") auth: String):Call <List<Place>>
}
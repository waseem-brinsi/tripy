package com.example.tripy_v1.Utils

import com.example.tripy_v1.Models.*
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface NodejsRetroService {

    //update User
    @POST("users")
    fun updateMe(@Header("authorization") auth: String,
                @Body user: User)
    :Call <UserUpdateResponse>

    @GET("users")
    fun getUsersList():Call <List<User>>

    @GET("users")
    fun searchUser(@Query(value = "email") searchText:String):Call<List<User>>

    @POST("users/signup")
    fun createUser(@Body user: User): Call<SignUpResponse>

    @POST("users/login")
    fun loginUser(@Body user: Login): Call<LoginResponse>

    @POST("users/forgotPassword")
    fun forgetPassword(@Body email: User): Call<ForgotResponse>

    @PATCH("users/resetcode/{RC}")
    fun resetcode(@Path("RC") RC:String?, @Body NewPassword :  User?):Call<NewPasswordResponse>
    //------------------------------------| Place Api |------------------------------------//
    //
    //
    //get Places
    @GET("places")
    fun getallplaces(@Header("authorization") auth: String):Call <List<Place>>

    @GET("places")
    fun searchPlace(@Query(value = "name") searchText:String,@Header("authorization") auth: String):Call <List<Place>>


    //Upload Image
    @Multipart
    @POST("upload")
    fun uploadImage(@Part image: MultipartBody.Part): Call<ImageResponse>


    @POST("places")
    fun createplace(@Body newplace: Place): Call<PlaceResponse>


    @POST("hotels")
    fun createhotel(@Body newhotel: Hotel): Call<HotelResponse>

}
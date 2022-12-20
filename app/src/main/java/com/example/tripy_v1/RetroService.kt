package com.example.tripy_v1

import com.example.tripy_v1.models.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface RetroService {

    @GET("users")
    fun getUsersList():Call <List<User>>

    @GET("users")
    fun searchUser(@Query(value = "name") searchText:String):Call<List<User>>

    @POST("users/signup")
    fun createUser(@Body user: User): Call<UserResponse>


    @POST("users/login")
    fun loginUser(@Body user: Login): Call<LoginResponse>

    @POST("users/forgotPassword")
    fun forgetPassword(@Body email: User): Call<ForgotResponse>


    @PATCH("users/resetcode/{RC}")
    fun resetcode(@Path("RC") RC:String?, @Body NewPassword :  User?):Call<NewPasswordResponse>



/*
    @Multipart
    @PUT("user/photo")
    fun updateUser(
        @Part("photo") photo: RequestBody?,
        @Part("description") description: RequestBody?
    ): Call<User?>?

 */
/*
    //=========  https://gorest.co.in   ========//
    //Done
    //https://gorest.co.in/public/v2/users/
    @GET("users")
    @Headers("Accept:application/json","Content-Type:application/json",
        "Authorization:Bearer 056327ec1a9ca8229a90066d77a8cb00305a0e1ebee916122991d5f10c3507fa")
    fun getUsersList():Call<List<User>>


    //Done
    //gorest.co.in/public/v2/users?name=Anwesha
    @GET("users")
    @Headers("Accept:application/json","Content-Type:application/json",
        "Authorization:Bearer 056327ec1a9ca8229a90066d77a8cb00305a0e1ebee916122991d5f10c3507fa")
    fun searchUser(@Query(value = "name") searchText:String):Call<List<User>>


    //Done
    //https://gorest.co.in/public/v2/users/3308
    @GET("users/{id}")
    @Headers("Accept:application/json","Content-Type:application/json",
        "Authorization:Bearer 056327ec1a9ca8229a90066d77a8cb00305a0e1ebee916122991d5f10c3507fa")
    fun searchUserById(@Path(value = "id") user_id: String):Call<List<User>>

    //Done
    @POST("users")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization:Bearer 056327ec1a9ca8229a90066d77a8cb00305a0e1ebee916122991d5f10c3507fa")
    fun createUser(@Body user:User): Call<UserResponse>


    //https://gorest.co.in/public/v2/users/27
    @PATCH("users/{id}")
    @Headers("Accept:application/json","Content-Type:application/json",
        "Authorization:Bearer 056327ec1a9ca8229a90066d77a8cb00305a0e1ebee916122991d5f10c3507fa"
    )
    fun updateUser(@Path("id")user_id:String,@Body user : User): Call<User>

    //https://gorest.co.in/public/v2/users/27
    @DELETE("users/{id}")
    @Headers("Accept:application/json","Content-Type:application/json",
        "Authorization:Bearer 056327ec1a9ca8229a90066d77a8cb00305a0e1ebee916122991d5f10c3507fa"
    )
    fun DeleteUser(@Path("id")user_id:String): Call<User>
    */
}
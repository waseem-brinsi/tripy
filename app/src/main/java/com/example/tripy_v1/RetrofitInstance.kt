package com.example.tripy_v1

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    //val baseUrl = "https://gorest.co.in/public/v2/"
    val baseUrl = "http://192.168.1.17:8000/api/v1/"
      fun getRetrofit():Retrofit{
          val logging = HttpLoggingInterceptor()
          logging.level = (HttpLoggingInterceptor.Level.BODY)
          val client = OkHttpClient.Builder()
          client.addInterceptor(logging)

          return Retrofit.Builder()
              .baseUrl(baseUrl)
              .client(client.build())
              .addConverterFactory(GsonConverterFactory.create())
              .build()
     }
}
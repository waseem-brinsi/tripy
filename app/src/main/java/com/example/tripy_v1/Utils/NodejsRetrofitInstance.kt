package com.example.tripy_v1.Utils


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object NodejsRetrofitInstance {
    val ServerUrl = "http://192.168.1.17:8000/"
    val baseUrl = ServerUrl+"api/v1/"
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
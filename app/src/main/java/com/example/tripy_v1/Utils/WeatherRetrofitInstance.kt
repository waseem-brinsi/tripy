package com.example.tripy_v1.Utils

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherRetrofitInstance {

    const val baseUrl = "https://api.openweathermap.org/data/2.5/"
    fun getRetrofit(): Retrofit {
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
package com.example.tripy_v1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.tripy_v1.R
import com.example.tripy_v1.RetroService
import com.example.tripy_v1.RetrofitInstance
import com.example.tripy_v1.models.Login
import com.example.tripy_v1.models.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    var etLoginEmail: EditText? = null
    var etLoginPassword: EditText? = null
    var btnBackLoginToMain:Button? = null
    var btnLogin:Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin = findViewById(R.id.btnLogin)
        btnLogin?.setOnClickListener {


            etLoginEmail = findViewById(R.id.etLoginEmail)
            etLoginPassword = findViewById(R.id.etLoginPassword)


            //Create User
            val login = Login(
                email= etLoginEmail?.text.toString(),
                password = etLoginPassword?.text.toString()
            )

            val retrofit = RetrofitInstance.getRetrofit()
            val retService : RetroService = retrofit.create(RetroService::class.java)

            var posted =  retService.LoginUser(login)
            posted.enqueue(object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    if(response.isSuccessful){
                        println("===============resault post================")
                        Log.d("resault", response.body().toString())
                    }
                }
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    println("===============resault post error================")
                    t.printStackTrace()
                }
            })

        }


        //Button Back
        btnBackLoginToMain = findViewById(R.id.btnBackLoginToMain)
        btnBackLoginToMain?.setOnClickListener {
            finish()
        }
    }
}
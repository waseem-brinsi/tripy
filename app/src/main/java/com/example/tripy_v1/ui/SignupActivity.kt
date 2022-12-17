package com.example.tripy_v1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.tripy_v1.R
import com.example.tripy_v1.RetroService
import com.example.tripy_v1.RetrofitInstance
import com.example.tripy_v1.models.User
import com.example.tripy_v1.models.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {

    var etName:EditText? = null
    var etEmail:EditText? = null
    var etrole:EditText? = null
    var etpassword:EditText? = null
    var etPasswordConfirm:EditText? =null

    var btnPostSingup:Button? = null
    var btnBackSingupToMain:Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        //Create Instance and Post

        btnPostSingup = findViewById(R.id.btnPostSignup)
        btnPostSingup?.setOnClickListener {
            etName = findViewById(R.id.etName)
            etEmail = findViewById(R.id.etEmail)
            etrole = findViewById(R.id.etRole)
            etpassword = findViewById(R.id.etPassword)
            etPasswordConfirm = findViewById(R.id.etPasswordConfirm)

            //Create User
            val user = User(
                role = etrole?.text.toString(),
                name = etName?.text.toString(),
                email= etEmail?.text.toString(),
                password = etpassword?.text.toString(),
                passwordConfirm = etPasswordConfirm?.text.toString()
            )

            val retrofit = RetrofitInstance.getRetrofit()
            val retService : RetroService = retrofit.create(RetroService::class.java)

            var posted =  retService.createUser(user)
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
        btnBackSingupToMain = findViewById(R.id.btnBackSingupToMain)
        btnBackSingupToMain?.setOnClickListener {
                finish()
        }

    }
}
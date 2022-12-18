package com.example.tripy_v1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.tripy_v1.R
import com.example.tripy_v1.RetroService
import com.example.tripy_v1.RetrofitInstance
import com.example.tripy_v1.models.Login
import com.example.tripy_v1.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    //properties
    private var btnSignup: Button? = null
    private var btnLoginMain: Button? = null
    private var btnGetallUser: Button? = null

    var etLoginEmail: EditText? = null
    var etLoginPassword: EditText? = null
    var btnToSignup:Button? = null
    var btnLogin:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val log = Login(
            email= etLoginEmail?.text.toString(),
            password = etLoginPassword?.text.toString()
        )

        Log.d("resault",log.email.toString())
        Log.d("resault",log.password.toString())
        val retrofit = RetrofitInstance.getRetrofit()
        val retService : RetroService = retrofit.create(RetroService::class.java)

        var loging =  retService.loginUser(log)
        loging.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful){
                    Log.d("resault", response.body().toString())
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("resault", t.message.toString())
            }
        })


        //Button
        btnGetallUser = findViewById(R.id.btnGetallUser)
        btnGetallUser?.setOnClickListener {
            Intent(this, GetallUsersActivity::class.java).also {
                startActivity(it)
            }
        }


        btnSignup = findViewById(R.id.btnSignup)
        btnSignup?.setOnClickListener {
            Intent(this, SignupActivity::class.java).also{
                startActivity(it)
            }
        }

        btnLoginMain = findViewById(R.id.btnLoginMain)
        btnLoginMain?.setOnClickListener {
            Intent(this, LoginActivity::class.java).also{
                startActivity(it)
            }
        }
    }

}



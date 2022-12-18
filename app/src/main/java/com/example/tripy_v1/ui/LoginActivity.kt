package com.example.tripy_v1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.tripy_v1.R
import com.example.tripy_v1.RetroService
import com.example.tripy_v1.RetrofitInstance
import com.example.tripy_v1.models.Login
import com.example.tripy_v1.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    var etLoginEmail: EditText? = null
    var etLoginPassword: EditText? = null
    var btnToSignup:Button? = null
    var btnLogin:Button? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Button Login
        btnLogin = findViewById(R.id.btnLogin)
        btnLogin?.setOnClickListener {
            etLoginEmail = findViewById(R.id.etLoginEmail)
            etLoginPassword = findViewById(R.id.etLoginPassword)
            //Create User
            val LogUser = Login(
                email= etLoginEmail?.text.toString(),
                password = etLoginPassword?.text.toString()
            )
            //viewModel - observe
            var viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
            viewModel.LoginLiveData.observe(this, Observer {
                Log.d("resault", it.toString())
            })
            viewModel.logging(LogUser)
        }

        //Button SignUp
        btnToSignup = findViewById(R.id.btnBackLoginToMain)
        btnToSignup?.setOnClickListener {
            Intent(this,SignupActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}
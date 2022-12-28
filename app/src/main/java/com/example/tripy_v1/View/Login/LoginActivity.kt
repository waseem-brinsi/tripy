package com.example.tripy_v1.View.Login


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tripy_v1.R
import com.example.tripy_v1.Models.Login
import com.example.tripy_v1.View.ForgotPassword.ForgotActivity
import com.example.tripy_v1.View.Home.HomeActivity
import com.example.tripy_v1.View.Signup.SignupActivity


class LoginActivity : AppCompatActivity() {
    var etLoginEmail: EditText? = null
    var etLoginPassword: EditText? = null
    var btnToSignup:Button? = null
    var btnLogin:Button? = null
    var tvForgotPassword:TextView? = null
    var Token :String? = null


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
                email= etLoginEmail?.text.toString().trim().lowercase(),
                password = etLoginPassword?.text.toString().trim().lowercase()
            )
            //viewModel - observe
            val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
            viewModel.LoginLiveData.observe(this, Observer {
                try {
                    val Token = it.token
                    if (Token!=null){
                        Intent(this, HomeActivity::class.java).also {
                            it.putExtra("EXT_Token",Token)
                            startActivity(it)
                            finish()
                        }
                    }
                }
                catch (e: Throwable) {
                    Log.d("resault",e.message.toString())
                    Toast.makeText(this, "Faild to login....Try Again", Toast.LENGTH_SHORT).show()
                }
            })
            viewModel.logging(LogUser)
        }
        //Button SignUp
        btnToSignup = findViewById(R.id.btnBackLoginToMain)
        btnToSignup?.setOnClickListener {
            Intent(this, SignupActivity::class.java).also {
                startActivity(it)
            }
        }

        // Button ForgotPassword
        tvForgotPassword = findViewById(R.id.tvForgotPassword)
        tvForgotPassword?.setOnClickListener {
            Intent(this, ForgotActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}
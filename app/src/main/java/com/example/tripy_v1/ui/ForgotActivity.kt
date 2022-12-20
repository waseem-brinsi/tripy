package com.example.tripy_v1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tripy_v1.R
import com.example.tripy_v1.models.User

class ForgotActivity : AppCompatActivity() {
    var btnForgotCancel : TextView? = null
    var etResetEmail : EditText? = null
    var btnSendCode : Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)



        //Button SendCode
        btnSendCode = findViewById(R.id.btnSendCode)
        btnSendCode?.setOnClickListener {
            etResetEmail = findViewById(R.id.etResetEmail)
            val email = etResetEmail?.text.toString().trim()
            val emailUser = User(
                email = email,
                name = null,
                phone = null,
                passwordConfirm = null,
                password = null
            )
            val viewModel = ViewModelProvider(this).get(ForgotActivityViewModel::class.java)
            viewModel.forgotLiveData.observe(this, Observer {
                Log.d("resault", it.toString())
            })
            viewModel.forgetPassword(emailUser)


            Intent(this,ResetActivity::class.java).also {
                it.putExtra("EXTRA_emailUser",emailUser)
                startActivity(it)
                finish()
            }


        }


        btnForgotCancel = findViewById(R.id.tvForgotCancel)
        btnForgotCancel?.setOnClickListener {

            finish()
        }


    }
}
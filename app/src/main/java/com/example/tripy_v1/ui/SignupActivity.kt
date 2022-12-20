package com.example.tripy_v1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tripy_v1.R
import com.example.tripy_v1.models.User

class SignupActivity : AppCompatActivity() {

    var etFirstName: EditText? = null
    var etLastName: EditText? = null
    var etEmail: EditText? = null
    var etpassword: EditText? = null
    var etPasswordConfirm: EditText? = null
    var etPhone: EditText? = null

    var btnPostSingup: Button? = null
    var btnSignupCancel: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        //Create Instance and Post
        btnPostSingup = findViewById(R.id.btnSignup)
        btnPostSingup?.setOnClickListener {
            etFirstName = findViewById(R.id.etFirstName)
            etLastName = findViewById(R.id.etLastName)
            etEmail = findViewById(R.id.etEmail)
            etpassword = findViewById(R.id.etPassword)
            etPasswordConfirm = findViewById(R.id.etPasswordConfirm)

            //Create User
            val SignUpuser = User(
                name = etFirstName?.text.toString()+" "+etLastName?.text.toString(),
                email = etEmail?.text.toString(),
                password = etpassword?.text.toString(),
                passwordConfirm = etPasswordConfirm?.text.toString(),
                phone = etPhone?.text.toString()
            )

            var viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
            viewModel.SignupLiveData.observe(this, Observer {
                Log.d("resault", it.toString())
            })
            viewModel.signup(SignUpuser)

            //Button Back
            btnSignupCancel = findViewById(R.id.btnSignupCancel)
            btnSignupCancel?.setOnClickListener {
                finish()
            }

        }
    }
}
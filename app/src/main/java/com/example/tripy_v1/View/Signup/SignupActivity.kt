package com.example.tripy_v1.View.Signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tripy_v1.R
import com.example.tripy_v1.Models.User
import com.example.tripy_v1.View.Home.HomeActivity
import com.example.tripy_v1.View.Login.LoginActivity

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


        //Sigup Button
        btnPostSingup = findViewById(R.id.btnSignup)
        btnPostSingup?.setOnClickListener {
            etFirstName = findViewById(R.id.etFirstName)
            etLastName = findViewById(R.id.etLastName)
            etEmail = findViewById(R.id.etEmail)
            etpassword = findViewById(R.id.etPassword)
            etPasswordConfirm = findViewById(R.id.etPasswordConfirm)
            etPhone = findViewById(R.id.etPhone)

            //Create User
            val SignUpuser = User(
                name = etFirstName?.text.toString().trim().lowercase()+" "+etLastName?.text.toString().trim().lowercase(),
                email = etEmail?.text.toString().trim().lowercase(),
                password = etpassword?.text.toString().trim().lowercase(),
                passwordConfirm = etPasswordConfirm?.text.toString().trim().lowercase(),
                phone = etPhone?.text.toString().trim().lowercase()
            )

            var viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
            viewModel.SignupLiveData.observe(this, Observer {
                Log.d("resault", it.toString())
                try {
                    var Token = it.token
                    if (Token!=null){
                        Intent(this, HomeActivity::class.java).also {
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
            viewModel.signup(SignUpuser)


        }


        //Cancel Button
        btnSignupCancel = findViewById(R.id.btnSignupCancel)
        btnSignupCancel?.setOnClickListener {
            Intent(this, LoginActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}
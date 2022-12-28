package com.example.tripy_v1.View.ForgotPassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tripy_v1.R
import com.example.tripy_v1.Models.User

class NewPasswordActivity : AppCompatActivity() {

    var btnUpdatePassword:Button? = null
    var etNewPassword:EditText? = null
    var etPasswordConfirm:EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_password)



        btnUpdatePassword = findViewById(R.id.btnUpdatePassword)
        btnUpdatePassword?.setOnClickListener {
            val code = intent.getStringExtra("EXT_Code")
            Log.d("code",code!!)
            etNewPassword = findViewById(R.id.etNewPassword)
            etPasswordConfirm = findViewById(R.id.etPasswordConfirm)



            var NewPassword = User(
                name = null,
                email = null,
                password = etNewPassword?.text.toString(),
                passwordConfirm = etPasswordConfirm?.text.toString(),
                phone = null
            )

            val viewModel = ViewModelProvider(this).get(NewPasswordViewModel::class.java)
            viewModel.NewPasswordActivity.observe(this, Observer {
                Log.d("resault", it.toString())
            })
            viewModel.UpdatePassword(code.toString(),NewPassword)



        }



    }
}
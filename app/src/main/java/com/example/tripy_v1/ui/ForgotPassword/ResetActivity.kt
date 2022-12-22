package com.example.tripy_v1.ui.ForgotPassword

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

class ResetActivity : AppCompatActivity() {
    var btnResend : Button? = null
    var etResetCode : EditText? = null
    var btnConfirm : Button? = null
    var tvResetCancel : TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset)




        btnConfirm = findViewById(R.id.btnConfirm)
        btnConfirm?.setOnClickListener {
            etResetCode = findViewById(R.id.etResetCode)
            val Code = etResetCode?.text.toString().trim()

            Intent(this, NewPasswordActivity::class.java).also {
                it.putExtra("EXT_Code",Code)
                startActivity(it)
                finish()
            }
        }


        btnResend?.setOnClickListener {
            etResetCode = findViewById(R.id.etResetCode)
            val emailUser = intent.getSerializableExtra("EXTRA_emailUser") as User
            btnResend = findViewById(R.id.btnResend)


            val viewModel = ViewModelProvider(this).get(ForgotActivityViewModel::class.java)
            viewModel.forgotLiveData.observe(this, Observer {
                Log.d("resault", it.toString())
            })
            viewModel.forgetPassword(emailUser)

        }

        tvResetCancel = findViewById(R.id.tvResetCancel)
        tvResetCancel?.setOnClickListener {
            finish()
        }


    }
}
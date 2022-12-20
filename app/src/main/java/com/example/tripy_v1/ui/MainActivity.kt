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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Button
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



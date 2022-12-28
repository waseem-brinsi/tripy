package com.example.tripy_v1.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import com.example.tripy_v1.R
import com.example.tripy_v1.View.Home.HomeActivity
import com.example.tripy_v1.View.Login.LoginActivity
import com.example.tripy_v1.View.Signup.SignupActivity


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    //properties
    private var btnSignup: Button? = null
    private var btnLoginMain: Button? = null
    private var btnGetallUser: Button? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val SPLASH_SCREEN_DELAY: Long = 3000 // 3 seconds

        supportActionBar?.hide()

        Handler().postDelayed({
            Intent(this, LoginActivity::class.java).also{
            startActivity(it)}
            finish()},SPLASH_SCREEN_DELAY
        )
/*
        btnSignup = findViewById(R.id.btnSignup)
        btnSignup?.setOnClickListener {
            Intent(this, SignupActivity::class.java).also{
                startActivity(it)
            }
        }


 */

    }

}



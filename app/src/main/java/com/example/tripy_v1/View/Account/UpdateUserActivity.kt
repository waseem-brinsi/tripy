package com.example.tripy_v1.View.Account

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tripy_v1.Models.User
import com.example.tripy_v1.R
import com.example.tripy_v1.Utils.NodejsRetrofitInstance
import com.example.tripy_v1.View.Home.HomeActivity
import com.example.tripy_v1.View.Login.LoginViewModel
import com.example.tripy_v1.View.NewPlaceAndHotel.NewHotelActivity
import com.example.tripy_v1.View.NewPlaceAndHotel.NewPlaceActivity
import com.squareup.picasso.Picasso

class UpdateUserActivity : AppCompatActivity() {
    lateinit var imgUpdateProfile : ImageView
    lateinit var tvUpdateName :TextView
    lateinit var tvUpdateEmail :TextView
    lateinit var tvUpdatePhone :TextView
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account)



        imgUpdateProfile = findViewById(R.id.imgUpdateProfile)
        tvUpdateName = findViewById(R.id.tvUpdateName)
        tvUpdateEmail = findViewById(R.id.tvUpdateEmail)
        tvUpdatePhone = findViewById(R.id.tvUpdatePhone)

        val Email = intent.getStringExtra("EXT_Email")
        Log.d("email", Email.toString())

        //viewModel - observe
        val viewModel = ViewModelProvider(this).get(UpdateUserViewModel::class.java)
        viewModel.UpdateUserLiveData.observe(this, Observer {
            Log.d("myuser", it.get(0).toString())
            if (it.get(0).photo !=null){
                val url = NodejsRetrofitInstance.ServerUrl +"img/"+ it.get(0).photo
                Picasso.get().load(url).into(imgUpdateProfile);
                Log.d("img",url)
            }


            tvUpdateName.text = "Name :"+it.get(0).name
            tvUpdateEmail.text = "Email :"+it.get(0).email
            tvUpdatePhone.text = "Phone :"+it.get(0).phone
        })
        if (Email != null) {
            viewModel.searchUser(Email)
        }

        //update Button
        val btnUpdateUser = findViewById<Button>(R.id.btnUpdateUser)
        btnUpdateUser?.setOnClickListener {
            showDialog(this)
        }

//------------------ Navigation ----------------------------------//
        //Button add
        val btnAddNew1:ImageView = findViewById(R.id.btnAddNew1)
        btnAddNew1?.setOnClickListener {
            showDialogAdd(this)
        }

        val btnHome1:ImageView = findViewById(R.id.btnHome1)
        btnHome1?.setOnClickListener {
            Intent(this,HomeActivity::class.java).also {
                it.putExtra("EXT_Email",Email)
                finish()
            }
        }


        //Button account
        val btnAccounte1:ImageView = findViewById(R.id.btnAccounte1)
        btnAccounte1?.setOnClickListener {
            Intent(this,UpdateUserActivity::class.java).also {
               //it.putExtra("EXT_Email",Email)
                startActivity(it)
            }
        }



    }

    fun showDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        val dialogView = layoutInflater.inflate(R.layout.dialog_input, null)
        builder.setTitle("Update User  ")
        builder.setView(dialogView).setMessage("Enter Updated Data")
        builder.setPositiveButton("New Place") { dialog, which ->


        }
        builder.setNegativeButton("cancel"){ dialog, which ->


        }
        val dialog = builder.create()
        dialog.show()
    }

    fun showDialogAdd(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Adding  ")
        builder.setMessage("Choosing Between Place Or Hotel")
        builder.setPositiveButton("New Place") { dialog, which ->
            Intent(context, NewPlaceActivity::class.java).also {
                startActivity(it)
            }
        }
        builder.setNeutralButton("New Hotel"){ dialog, which ->

            Intent(context, NewHotelActivity::class.java).also {
                startActivity(it)
            }
        }
        val dialog = builder.create()
        dialog.show()
    }
}
package com.example.tripy_v1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tripy_v1.R
import com.example.tripy_v1.UserAdaptor

class GetallUsersActivity : AppCompatActivity() {
    var btnBackGetusersToMain:Button? = null
    var btnSearch:Button? = null
    var etSearchUser:EditText? = null
    var name:String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getall_users)


        //allusers
        var viewModel = ViewModelProvider(this).get(GetallUsersViewModel::class.java)
        viewModel.UsersLiveData.observe(this, Observer {
            Log.d("resault", it.toString())
            val rvUsers: RecyclerView? = findViewById(R.id.rvUsers)
            rvUsers?.adapter =  UserAdaptor(it)
            rvUsers?.layoutManager = LinearLayoutManager(this@GetallUsersActivity)
        })
        viewModel.getUsersList()








        //Button Search -- users by name
        etSearchUser = findViewById(R.id.etSearchUser)
        btnSearch = findViewById(R.id.btnSearch)

        btnSearch?.setOnClickListener {
            var name = etSearchUser?.text.toString().lowercase().trim()
            viewModel.UsersLiveData.observe(this, Observer {
                Log.d("resault", it.toString())
                val rvUsers: RecyclerView? = findViewById(R.id.rvUsers)
                rvUsers?.adapter = UserAdaptor(it)
                rvUsers?.layoutManager = LinearLayoutManager(this@GetallUsersActivity)
            })
            viewModel.GetUserByName(name)








        }


        //Button Back
        btnBackGetusersToMain = findViewById(R.id.btnBackGetusersToMain)
        btnBackGetusersToMain?.setOnClickListener {
            Intent(this, GetallUsersActivity::class.java).also {
                finish()
            }
        }


    }
}
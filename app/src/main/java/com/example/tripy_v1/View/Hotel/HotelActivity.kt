package com.example.tripy_v1.View.Hotel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tripy_v1.Adaptor.DiscoverAdaptor
import com.example.tripy_v1.Adaptor.PlaceAdaptor
import com.example.tripy_v1.Models.Country
import com.example.tripy_v1.R
import com.example.tripy_v1.View.Account.UpdateUserActivity
import com.example.tripy_v1.View.Home.HomeActivity
import com.example.tripy_v1.View.Home.HomeViewModel
import com.example.tripy_v1.View.Home.HomeWeatherViewModel
import com.squareup.picasso.Picasso

class HotelActivity : AppCompatActivity() {

    var btnAccounte:ImageView? = null
    var btnAddNew:ImageView? = null
    var btnSearch: Button? = null
    var etSearchUser: EditText? = null
    var tvWeatherLocation1: TextView? = null
    var tvWeatherDetail1: TextView? = null
    var imgWeather:ImageView? = null
    var name:String? = null
    var Token:String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel)

        //View Model Weather
        val WeatherViewModel = ViewModelProvider(this).get(HomeWeatherViewModel::class.java)
        WeatherViewModel.WeatherLiveData.observe(this, Observer {
            Log.d("weather", it.toString())
            tvWeatherLocation1 = findViewById(R.id.tvWeatherLocation1)
            tvWeatherDetail1 = findViewById(R.id.tvWeatherDetail1)
            imgWeather = findViewById(R.id.imgWeather)

            tvWeatherLocation1?.text = it.sys.country+","+it.name
            val deg = it.main.temp.toString()
            tvWeatherDetail1?.text = it.weather[0].description+","+deg.subSequence(0,2)

            val url = "https://openweathermap.org/img/wn/"+it.weather[0].icon+".png"
            Picasso.get().load(url).into(imgWeather);
        })
        var lat="36.80"
        var lon="10.1815"
        var appid="ced230492360320abde50c50739ea98c"
        WeatherViewModel.GetWeather(lat,lon,appid)




        val Email = intent.getStringExtra("EXT_Email")
        val code = intent.getStringExtra("EXT_Token")

        //all Places - view model

        Token ="name "+code
        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.PlacesLiveData.observe(this, Observer {
            Log.d("resault", it.toString())
            val rvUsers: RecyclerView? = findViewById(R.id.rvPlaces)
            rvUsers?.adapter =  PlaceAdaptor(it)
            rvUsers?.layoutManager = GridLayoutManager(this,2)
        })
        viewModel.getPlacesList(Token!!)


        //Button Search -- Places by name
        etSearchUser = findViewById(R.id.etSearchUser)
        btnSearch = findViewById(R.id.btnSearch)
        btnSearch?.setOnClickListener {
            val name = etSearchUser?.text.toString().lowercase().trim()
            viewModel.PlacesLiveData.observe(this, Observer {
                Log.d("resault", it.toString())
                val rvUsers: RecyclerView? = findViewById(R.id.rvPlaces)
                rvUsers?.adapter = PlaceAdaptor(it)
                rvUsers?.layoutManager = GridLayoutManager(this,2)
            })
            viewModel.GetPlaceByName(name,Token!!)
        }


        //Button home
        val btnHome: ImageView = findViewById(R.id.btnHome)
        btnHome?.setOnClickListener {
            Intent(this, HomeActivity::class.java).also {
                it.putExtra("EXT_Token",code)
                it.putExtra("EXT_Token",Email)
                startActivity(it)
                finish()
            }
        }


        //Button add
        btnAddNew = findViewById(R.id.btnAddNew)
        btnAddNew?.setOnClickListener {
            //showDialog(this)
        }

        //Button update
        btnAccounte = findViewById(R.id.btnAccounte)
        btnAccounte?.setOnClickListener {
            Intent(this, UpdateUserActivity::class.java).also {
                it.putExtra("EXT_Email",Email)
                startActivity(it)
            }
        }



    }
}
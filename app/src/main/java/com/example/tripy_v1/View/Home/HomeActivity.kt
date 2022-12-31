package com.example.tripy_v1.View.Home

import android.annotation.SuppressLint
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
import com.example.tripy_v1.R
import com.example.tripy_v1.Adaptor.PlaceAdaptor
import com.example.tripy_v1.Models.Country
import com.squareup.picasso.Picasso


class HomeActivity : AppCompatActivity() {
    var btnBackGetusersToMain:Button? = null
    var btnSearch:Button? = null
    var etSearchUser:EditText? = null
    var tvWeatherLocation:TextView? = null
    var tvWeatherDetail:TextView? = null
    var imgWeather:ImageView? = null
    var name:String? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)



        //View Model Weather
        val WeatherViewModel = ViewModelProvider(this).get(HomeWeatherViewModel::class.java)
        WeatherViewModel.WeatherLiveData.observe(this, Observer {
            Log.d("weather", it.toString())
            tvWeatherLocation = findViewById(R.id.tvWeatherLocation)
            tvWeatherDetail = findViewById(R.id.tvWeatherDetail)
            imgWeather = findViewById(R.id.imgWeather)

            tvWeatherLocation?.text = it.sys.country+","+it.name
            val deg = it.main.temp.toString()
            tvWeatherDetail?.text = it.weather[0].description+","+deg.subSequence(0,2)

            val url = "https://openweathermap.org/img/wn/"+it.weather[0].icon+".png"
            Picasso.get().load(url).into(imgWeather);
        })
        var lat="36.80"
        var lon="10.1815"
        var appid="ced230492360320abde50c50739ea98c"
        WeatherViewModel.GetWeather(lat,lon,appid)


        //Discover - list of Country
        val listCountry:MutableList<Country> = arrayListOf()
        listCountry.add(Country("Algeria",R.drawable.algeria))
        listCountry.add(Country("egypt", R.drawable.egypt))
        listCountry.add(Country("germany", R.drawable.germany))
        listCountry.add(Country("morocco", R.drawable.morocco))
        listCountry.add(Country("tunisia", R.drawable.tunisia))
        listCountry.add(Country("russia", R.drawable.russia))
        val rvDiscover :RecyclerView? = findViewById(R.id.rvDiscover)
        rvDiscover?.adapter = DiscoverAdaptor(listCountry)
        rvDiscover?.layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)



        //all Places - view model
        var Token ="name "+intent.getStringExtra("EXT_Token")
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
            viewModel.GetPlaceByName(name,Token)
        }



        //Button Back
        btnBackGetusersToMain = findViewById(R.id.btnBackGetusersToMain)
        btnBackGetusersToMain?.setOnClickListener {
            Intent(this, HomeActivity::class.java).also {
                finish()
            }
        }
    }
}
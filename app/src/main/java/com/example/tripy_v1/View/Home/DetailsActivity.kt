package com.example.tripy_v1.View.Home

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tripy_v1.R
import com.squareup.picasso.Picasso


class DetailsActivity : AppCompatActivity() {

    private var tvTitleDetails :TextView? = null
    private var tvDescriptionDetails :TextView? = null
    private var tvRatingDetails :TextView? = null
    var imgDetails :ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)



        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val name = intent.getStringExtra("EXT_name")
        val description = intent.getStringExtra("EXT_description")
        val rating = intent.getStringExtra("EXT_rating")
        val imgUrl = intent.getStringExtra("EXT_img")
        Log.d("imgUrl",imgUrl.toString())

        tvTitleDetails = findViewById(R.id.tvTitleDetails)
        tvDescriptionDetails = findViewById(R.id.tvDescriptionDetails)
        tvRatingDetails = findViewById(R.id.tvRatingDetails)
        imgDetails = findViewById(R.id.imgDetails)

        tvTitleDetails?.text = name
        tvDescriptionDetails?.text = description
        tvRatingDetails?.text = rating
        Picasso.get().load(imgUrl).into(imgDetails)



        supportActionBar?.setTitle(name)


    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
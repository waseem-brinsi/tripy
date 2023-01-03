package com.example.tripy_v1.View.NewPlaceAndHotel

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tripy_v1.Models.Hotel
import com.example.tripy_v1.Models.ImageResponse
import com.example.tripy_v1.Models.Place
import com.example.tripy_v1.R
import com.example.tripy_v1.Utils.NodejsRetroService
import com.example.tripy_v1.Utils.NodejsRetrofitInstance
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

@Suppress("DEPRECATION", "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class NewHotelActivity : AppCompatActivity() {
    private lateinit var imgNewHotel : ImageView
    private lateinit var btnNewHotelAdd : Button
    private lateinit var etNewHotelName : EditText
    private lateinit var etNewHotelDescription : EditText
    private lateinit var etHotelRating : EditText
    private lateinit var Myphoto : String
    private lateinit var image : MultipartBody.Part



    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_new_hotel)


            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setTitle("Add New Hotel")


            imgNewHotel = findViewById(R.id.imgNewHotel)
            imgNewHotel.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, 100)
            }


                //Sigup Button
            btnNewHotelAdd = findViewById(R.id.btnNewHotelAdd)
            btnNewHotelAdd.setOnClickListener {
                etNewHotelName = findViewById(R.id.etNewHotelName)
                etNewHotelDescription = findViewById(R.id.etNewHotelDescription)
                etHotelRating = findViewById(R.id.etHotelRating)

                    Log.d("image",Myphoto)
                    //Create User
                    val newhotel = Hotel(
                        image = Myphoto,
                        name = etNewHotelName.text.toString().trim().lowercase(),
                        address = etNewHotelName.text.toString().trim().lowercase(),
                        description = etNewHotelDescription.text.toString().trim().lowercase(),
                        rating = etHotelRating.text.toString().trim().lowercase()
                    )

                    var viewModel = ViewModelProvider(this).get(NewHotelViewModel::class.java)
                    viewModel.NewHotelLiveData.observe(this, Observer {
                        Log.d("resault", it.toString())
                    })
                    viewModel.AddNewHotel(newhotel)
                }

            //Cancel Button
            val btnNewHotelCancel = findViewById<Button>(R.id.btnNewHotelCancel)
            btnNewHotelCancel?.setOnClickListener {
                finish()
            }
        }



        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
                if (data == null || data.data == null) {
                    return
                }
                val imguri = data.data
                val REQUEST_STORAGE_PERMISSIONS = 1
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                    // Permissions are not granted
                    // Request permissions
                    ActivityCompat.requestPermissions(this, arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_STORAGE_PERMISSIONS)
                } else {
                    // Permissions have already been granted
                    // Do your work here


                    val file = File(imguri?.let { filePath(it) })
                    Log.d("imguri", file.toString())
                    imgNewHotel.setImageURI(imguri)
                    val requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
                    image = MultipartBody.Part.createFormData("image", file.name, requestFile)
                }
                val retrofit = NodejsRetrofitInstance.getRetrofit()
                val retService : NodejsRetroService = retrofit.create(NodejsRetroService::class.java)
                val call = retService.uploadImage(image)
                call.enqueue(object : Callback<ImageResponse> {
                    override fun onResponse(call: Call<ImageResponse>, response: Response<ImageResponse>){
                        Myphoto = response.body()?.filename.toString()
                        Log.d("imguri", response.body().toString())
                    }
                    override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                        Log.d("imguri", t.message.toString())
                    }
                })
            }
        }

        private fun filePath(uri: Uri):String?{
            var filePath: String? = null
            val cursor = uri.let { contentResolver.query(it, null, null, null, null) }
            if (cursor != null) {
                cursor.moveToFirst()
                val columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                filePath = cursor.getString(columnIndex)
                cursor.close()
            }
            return  filePath
        }



    }

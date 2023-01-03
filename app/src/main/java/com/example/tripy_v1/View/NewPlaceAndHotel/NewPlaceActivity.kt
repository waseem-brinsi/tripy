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
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tripy_v1.Models.ImageResponse
import com.example.tripy_v1.Models.Place
import com.example.tripy_v1.Models.User
import com.example.tripy_v1.R
import com.example.tripy_v1.Utils.NodejsRetroService
import com.example.tripy_v1.Utils.NodejsRetrofitInstance
import com.example.tripy_v1.View.Home.HomeActivity
import com.example.tripy_v1.View.Signup.SignupViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

@Suppress("DEPRECATION")
class NewPlaceActivity : AppCompatActivity() {


    private lateinit var imgNewPlace : ImageView
    private lateinit var btnNewPlaceAdd : Button
    private lateinit var etNewPlaceName : EditText
    private lateinit var etNewPlaceDescription : EditText
    private lateinit var etPlaceRating : EditText
    private lateinit var Myphoto : String
    private lateinit var image : MultipartBody.Part



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_place)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Add New Place")

        imgNewPlace = findViewById(R.id.imgNewPlace)
        imgNewPlace.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 100)
        }


        //Sigup Button
        btnNewPlaceAdd = findViewById(R.id.btnNewPlaceAdd)
        btnNewPlaceAdd?.setOnClickListener {
            etNewPlaceName = findViewById(R.id.etNewPlaceName)
            etNewPlaceDescription = findViewById(R.id.etNewPlaceDescription)
            etPlaceRating = findViewById(R.id.etPlaceRating)

            Log.d("image",Myphoto)
            //Create User
            val newplace = Place(
                image = Myphoto,
                name = etNewPlaceName?.text.toString().trim().lowercase(),
                description = etNewPlaceDescription?.text.toString().trim().lowercase(),
                rating = etPlaceRating?.text.toString().trim().lowercase()
            )


            var viewModel = ViewModelProvider(this).get(NewPlaceViewModel::class.java)
            viewModel.NewPlaceLiveData.observe(this, Observer {
                Log.d("resault", it.toString())
            })
            viewModel.AddNewPlace(newplace)

        }
        //Cancel Button
        val btnNewPlaceCancel = findViewById<Button>(R.id.btnNewPlaceCancel)
        btnNewPlaceCancel?.setOnClickListener {
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
                imgNewPlace?.setImageURI(imguri)
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
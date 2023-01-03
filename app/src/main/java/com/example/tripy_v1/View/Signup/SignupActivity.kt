package com.example.tripy_v1.View.Signup


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
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tripy_v1.Models.ImageResponse
import com.example.tripy_v1.R
import com.example.tripy_v1.Models.User
import com.example.tripy_v1.Utils.NodejsRetroService
import com.example.tripy_v1.Utils.NodejsRetrofitInstance
import com.example.tripy_v1.View.Home.HomeActivity
import com.example.tripy_v1.View.Login.LoginActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

@Suppress("DEPRECATION")
class SignupActivity : AppCompatActivity() {

    var imgSignup: ImageView? = null
    lateinit var image : MultipartBody.Part
    var etFirstName: EditText? = null
    var etLastName: EditText? = null
    var etEmail: EditText? = null
    var etpassword: EditText? = null
    var etPasswordConfirm: EditText? = null
    var etPhone: EditText? = null
    var btnPostSingup: Button? = null
    var btnSignupCancel: Button? = null
    lateinit var Myphoto :String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        imgSignup = findViewById(R.id.imgSignup)
        imgSignup?.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 100)
        }

        //Sigup Button
        btnPostSingup = findViewById(R.id.btnSignup)
        btnPostSingup?.setOnClickListener {
            etFirstName = findViewById(R.id.etFirstName)
            etLastName = findViewById(R.id.etLastName)
            etEmail = findViewById(R.id.etEmail)
            etpassword = findViewById(R.id.etPassword)
            etPasswordConfirm = findViewById(R.id.etPasswordConfirm)
            etPhone = findViewById(R.id.etPhone)

            Log.d("Myphoto",Myphoto)
            //Create User
            val SignUpuser = User(
                    photo = Myphoto,
                    name = etFirstName?.text.toString().trim().lowercase()+" "+etLastName?.text.toString().trim().lowercase(),
                    email = etEmail?.text.toString().trim().lowercase(),
                    password = etpassword?.text.toString().trim().lowercase(),
                    passwordConfirm = etPasswordConfirm?.text.toString().trim().lowercase(),
                    phone = etPhone?.text.toString().trim().lowercase()
            )

            var viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
            viewModel.SignupLiveData.observe(this, Observer {
                Log.d("resault", it.toString())
                try {
                    var Token = it.token
                    if (Token!=null){
                        Intent(this, HomeActivity::class.java).also {
                            it.putExtra("EXT_Token",Token)
                            startActivity(it)
                            finish()
                        }
                    }
                }
                catch (e: Throwable) {
                    Log.d("resault",e.message.toString())
                    Toast.makeText(this, "Faild to login....Try Again", Toast.LENGTH_SHORT).show()
                }
            })
            viewModel.signup(SignUpuser)
        }


        //Cancel Button
        btnSignupCancel = findViewById(R.id.btnSignupCancel)
        btnSignupCancel?.setOnClickListener {
            Intent(this, LoginActivity::class.java).also {
                startActivity(it)
                finish()
            }
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
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_STORAGE_PERMISSIONS)
                } else {
                    // Permissions have already been granted
                    // Do your work here

                    val file = File(imguri?.let { filePath(it) })
                    Log.d("imguri", file.toString())
                    imgSignup?.setImageURI(imguri)
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

    private fun filePath(uri:Uri):String?{
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
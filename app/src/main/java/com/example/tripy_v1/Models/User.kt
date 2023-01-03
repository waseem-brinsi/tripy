package com.example.tripy_v1.Models

import java.io.Serializable

data class User(
    val photo:String?,
    val name: String?,
    val email:String?,
    val password:String?,
    val passwordConfirm:String?,
    val phone:String?
    ):Serializable
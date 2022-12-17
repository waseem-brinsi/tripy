package com.example.tripy_v1.models


data class UserResponse(
    val code : Int,
    val meta : String,
    val data: User
)
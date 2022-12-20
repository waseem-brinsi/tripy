package com.example.tripy_v1.models


data class UserResponse(
    val status : String,
    val meta : String,
    val data: User
)
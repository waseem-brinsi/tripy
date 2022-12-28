package com.example.tripy_v1.Models

data class SignUpResponse(
    val `data`: User,
    val status: String,
    val token: String
)

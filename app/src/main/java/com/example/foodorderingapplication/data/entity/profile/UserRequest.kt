package com.example.foodorderingapplication.data.entity.profile

import com.google.gson.annotations.SerializedName

data class UserRequest (
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("place")
    val address: String,
    @SerializedName("phone")
    val phone: String,
)
package com.example.foodorderingapplication.data.entity.profile

import com.example.foodorderingapplication.data.entity.User
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val user: User,
    @SerializedName("success")
    val success: Boolean
)
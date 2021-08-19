package com.example.foodorderingapplication.data.entity.restaurant

import com.google.gson.annotations.SerializedName

data class OrderRestaurant(
    @SerializedName("name")
    val name: String)
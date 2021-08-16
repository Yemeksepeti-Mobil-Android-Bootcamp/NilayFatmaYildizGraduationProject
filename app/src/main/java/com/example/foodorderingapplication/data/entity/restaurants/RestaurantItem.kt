package com.example.foodorderingapplication.data.entity.restaurants


import com.google.gson.annotations.SerializedName

data class RestaurantItem(
    @SerializedName("address")
    val address: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("score")
    val score: String
)
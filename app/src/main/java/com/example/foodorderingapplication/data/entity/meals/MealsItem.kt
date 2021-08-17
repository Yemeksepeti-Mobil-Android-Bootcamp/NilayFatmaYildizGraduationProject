package com.example.foodorderingapplication.data.entity.meals


import com.google.gson.annotations.SerializedName

data class MealsItem(
    @SerializedName("count")
    val count: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("ingredients")
    val ingredients: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("restaurantId")
    val restaurantId: String
)
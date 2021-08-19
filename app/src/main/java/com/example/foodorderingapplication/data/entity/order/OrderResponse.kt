package com.example.foodorderingapplication.data.entity.order

import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("data")
    val orderList: ArrayList<Order>,
    val success: Boolean
)
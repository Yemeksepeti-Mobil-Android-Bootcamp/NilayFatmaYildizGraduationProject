package com.example.foodorderingapplication.ui.listeners

import com.example.foodorderingapplication.data.entity.restaurant.Restaurant

interface IRestaurantClickListener {
    fun onClick(name: Restaurant)
}
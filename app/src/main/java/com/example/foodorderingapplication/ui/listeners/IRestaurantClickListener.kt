package com.example.foodorderingapplication.ui.listeners

import com.example.foodorderingapplication.data.entity.restaurants.RestaurantItem

interface IRestaurantClickListener {
    fun onClick(name: RestaurantItem)
}
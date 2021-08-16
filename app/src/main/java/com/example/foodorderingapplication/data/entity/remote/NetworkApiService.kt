package com.example.foodorderingapplication.data.entity.remote

import com.example.foodorderingapplication.data.entity.restaurants.Restaurant
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApiService {
    @GET("restaurants")
    suspend fun getRestaurants(): Response<Restaurant>
}
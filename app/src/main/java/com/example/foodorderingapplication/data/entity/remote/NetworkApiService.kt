package com.example.foodorderingapplication.data.entity.remote

import com.example.foodorderingapplication.data.entity.meals.Meals
import com.example.foodorderingapplication.data.entity.restaurants.Restaurants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApiService {
    @GET("restaurants")
    suspend fun getRestaurants(): Response<Restaurants>

    @GET("restaurants/{id}/meals")
    suspend fun getMeals(@Path("id")id: Int): Response<Meals>

    @GET("restaurants")
    suspend fun searchRestaurants(@Query("search")searchKey:String):Response<Restaurants>
}
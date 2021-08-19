package com.example.foodorderingapplication.data.entity.remote

import com.example.foodorderingapplication.data.entity.login.LoginRequest
import com.example.foodorderingapplication.data.entity.login.LoginResponse
import com.example.foodorderingapplication.data.entity.meal.MealResponse
import com.example.foodorderingapplication.data.entity.restaurant.RestaurantListResponse
import com.example.foodorderingapplication.data.entity.restaurant.RestaurantResponse
import retrofit2.Response
import retrofit2.http.*

interface NetworkApiService {
    @GET("a/restaurant")
    suspend fun getRestaurants(): Response<RestaurantListResponse>

    @GET("a/restaurant/cuisine/{cuisineName}")
    suspend fun getRestaurantsByCuisine(@Path("cuisineName") cuisine: String): Response<RestaurantListResponse>

    @GET("a/restaurant/{id}")
    suspend fun getRestaurantById(@Path("id") id: String): Response<RestaurantResponse>

    @GET("a/meal/{id}")
    suspend fun getMealById(@Path("id") id: String): Response<MealResponse>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}
package com.example.foodorderingapplication.data.entity.remote

import com.example.foodorderingapplication.data.entity.login.LoginRequest
import com.example.foodorderingapplication.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: NetworkApiService) : BaseDataSource() {

    suspend fun getRestaurants() = getResult { apiService.getRestaurants() }

    suspend fun getRestaurantsByCuisine(cuisine: String) =
        getResult { apiService.getRestaurantsByCuisine(cuisine) }

    suspend fun getRestaurantById(id: String) = getResult { apiService.getRestaurantById(id) }

    suspend fun getMealById(id: String) = getResult { apiService.getMealById(id) }

    suspend fun postLogin(request: LoginRequest) = getResult {
        apiService.login(request)
    }

}

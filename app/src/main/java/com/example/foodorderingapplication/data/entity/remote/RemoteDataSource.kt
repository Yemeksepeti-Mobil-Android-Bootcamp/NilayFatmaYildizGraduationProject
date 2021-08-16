package com.example.foodorderingapplication.data.entity.remote

import com.example.foodorderingapplication.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: NetworkApiService) :
    BaseDataSource(){

    suspend fun fetchRestaurants() = getResult { apiService.getRestaurants() }

}

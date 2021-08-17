package com.example.foodorderingapplication.repository

import com.example.foodorderingapplication.data.entity.remote.RemoteDataSource
import com.example.foodorderingapplication.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
){

    fun getRestaurantList() = performNetworkOperation {
        remoteDataSource.fetchRestaurants()
    }

    fun getMealList(id:String) = performNetworkOperation {
        remoteDataSource.fetchMeals(id)
    }
}
package com.example.foodorderingapplication.data.remote

import com.example.foodorderingapplication.data.entity.order.OrderAddRequest
import com.example.foodorderingapplication.data.entity.profile.UserRequest
import com.example.foodorderingapplication.utils.BaseDataSource
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val authAPIService: AuthAPIService) :
    BaseDataSource() {

    suspend fun getOrders() = getResult { authAPIService.getOrders() }

    suspend fun updateUser(request : UserRequest) = getResult { authAPIService.updateUser(request) }

    suspend fun getUser() = getResult { authAPIService.getUser() }

    suspend fun postOrder(orderAddRequest: OrderAddRequest) = getResult {
        authAPIService.postOrder(orderAddRequest)
    }

}
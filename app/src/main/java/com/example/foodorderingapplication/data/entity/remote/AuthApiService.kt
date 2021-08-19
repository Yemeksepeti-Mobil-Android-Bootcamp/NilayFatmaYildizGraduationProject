package com.example.foodorderingapplication.data.entity.remote

import com.example.foodorderingapplication.data.entity.User
import com.example.foodorderingapplication.data.entity.order.OrderAddRequest
import com.example.foodorderingapplication.data.entity.order.OrderAddResponse
import com.example.foodorderingapplication.data.entity.order.OrderResponse
import com.example.foodorderingapplication.data.entity.profile.UserRequest
import com.example.foodorderingapplication.data.entity.profile.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface AuthAPIService {

    @POST("a/order")
    suspend fun postOrder(@Body request: OrderAddRequest): Response<OrderAddResponse>

    @GET("a/order")
    suspend fun getOrders(): Response<OrderResponse>

    @PUT("auth/updateDetails")
    suspend fun updateUser(@Body request : UserRequest) : Response<User>

    @GET("auth/profile")
    suspend fun getUser() : Response<UserResponse>

}
package com.example.foodorderingapplication.repository

import com.example.foodorderingapplication.data.entity.local.LocalDataSource
import com.example.foodorderingapplication.data.entity.login.LoginRequest
import com.example.foodorderingapplication.data.entity.order.OrderAddRequest
import com.example.foodorderingapplication.data.entity.profile.UserRequest
import com.example.foodorderingapplication.data.entity.register.RegisterRequest
import com.example.foodorderingapplication.data.remote.AuthRemoteDataSource
import com.example.foodorderingapplication.data.remote.RemoteDataSource
import com.example.foodorderingapplication.utils.performAuthTokenNetworkOperation
import com.example.foodorderingapplication.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private var authRemoteDataSource: AuthRemoteDataSource,
    private var localDataSource: LocalDataSource
) {

    fun login(request: LoginRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.postLogin(request)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )

    fun register(request: RegisterRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.postRegister(request)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )

    fun getRestaurants() =
        performNetworkOperation {
            remoteDataSource.getRestaurants()
        }

    fun getRestaurantById(id: String) =
        performNetworkOperation {
            remoteDataSource.getRestaurantById(id)
        }

    fun getMealById(id: String) =
        performNetworkOperation {
            remoteDataSource.getMealById(id)
        }

    fun getRestaurantByCuisine(cuisine: String) =
        performNetworkOperation {
            remoteDataSource.getRestaurantsByCuisine(cuisine)
        }


    fun getOrder() =
        performNetworkOperation {
            authRemoteDataSource.getOrders()
        }

    fun getUser() = performNetworkOperation {
        authRemoteDataSource.getUser()
    }

    fun updateUser(user : UserRequest) = performNetworkOperation {
        authRemoteDataSource.updateUser(request = user)
    }

    fun postOrder(orderAddRequest: OrderAddRequest) =
        performNetworkOperation {
            authRemoteDataSource.postOrder(orderAddRequest)
        }

    fun logOut() {
        localDataSource.saveToken("")
    }

}
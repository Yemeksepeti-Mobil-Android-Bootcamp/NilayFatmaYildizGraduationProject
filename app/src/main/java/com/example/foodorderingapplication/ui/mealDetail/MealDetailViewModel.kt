package com.example.foodorderingapplication.ui.mealDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.foodorderingapplication.data.entity.meal.Meal
import com.example.foodorderingapplication.data.entity.meal.MealResponse
import com.example.foodorderingapplication.data.entity.order.OrderAddRequest
import com.example.foodorderingapplication.data.entity.order.OrderAddResponse
import com.example.foodorderingapplication.repository.ApiRepository
import com.example.foodorderingapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {
    var meal: Meal? = null

    fun getMealDetails(id: String): LiveData<Resource<MealResponse>> {
        return apiRepository.getMealById(id)
    }

    fun postOrder(orderAddRequest: OrderAddRequest): LiveData<Resource<OrderAddResponse>> =
        apiRepository.postOrder(orderAddRequest)
}
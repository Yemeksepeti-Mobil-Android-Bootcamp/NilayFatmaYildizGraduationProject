package com.example.foodorderingapplication.ui.restaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.foodorderingapplication.data.entity.restaurants.Restaurants
import com.example.foodorderingapplication.repository.ApiRepository
import com.example.foodorderingapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantListViewModel @Inject constructor(

    val savedStateHandle: SavedStateHandle,
    val apiRepository: ApiRepository
) : ViewModel() {
    fun fetchRestaurantList(): LiveData<Resource<Restaurants>> =
        apiRepository.getRestaurantList()
}
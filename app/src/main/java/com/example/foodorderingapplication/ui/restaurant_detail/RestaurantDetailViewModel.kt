package com.example.foodorderingapplication.ui.restaurant_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.foodorderingapplication.data.entity.meals.Meals
import com.example.foodorderingapplication.repository.ApiRepository
import com.example.foodorderingapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealListViewModel @Inject constructor(

    val savedStateHandle: SavedStateHandle,
    val apiRepository: ApiRepository
) : ViewModel() {

    fun fetchMealList(id:String): LiveData<Resource<Meals>> =
        apiRepository.getMealList(id)
}
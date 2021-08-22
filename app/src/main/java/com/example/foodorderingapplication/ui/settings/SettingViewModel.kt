package com.example.foodorderingapplication.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.foodorderingapplication.data.entity.User
import com.example.foodorderingapplication.data.entity.profile.UserRequest
import com.example.foodorderingapplication.data.entity.profile.UserResponse
import com.example.foodorderingapplication.repository.ApiRepository
import com.example.foodorderingapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompleteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun getUser(): LiveData<Resource<UserResponse>> = apiRepository.getUser()

    fun updateUser(userRequest: UserRequest): LiveData<Resource<User>> =
        apiRepository.updateUser(userRequest)

}
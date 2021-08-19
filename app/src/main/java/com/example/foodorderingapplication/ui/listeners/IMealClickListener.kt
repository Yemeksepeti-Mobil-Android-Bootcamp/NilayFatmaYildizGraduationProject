package com.example.foodorderingapplication.ui.listeners

import com.example.foodorderingapplication.data.entity.meal.Meal

interface IMealClickListener {
    fun onClick(name: Meal)
}
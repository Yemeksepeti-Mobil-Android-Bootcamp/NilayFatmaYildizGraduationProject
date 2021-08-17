package com.example.foodorderingapplication.ui.listeners

import com.example.foodorderingapplication.data.entity.meals.MealsItem

interface IMealClickListener {
    fun onClick(name: MealsItem)
}
package com.example.foodorderingapplication.ui.mealDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderingapplication.databinding.ItemIngredientBinding

class MealIngredientsAdapter :
    RecyclerView.Adapter<MealIngredientsAdapter.MealIngredientsViewHolder>() {

    private var ingredients = ArrayList<String>()

    class MealIngredientsViewHolder(val binding: ItemIngredientBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setIngredient(ingredient: String) {
            binding.ingredientTextView.text = ingredient
        }
    }


    fun setIngredients(ingredientList: ArrayList<String>) {
        this.ingredients = ingredientList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealIngredientsViewHolder {
        return MealIngredientsAdapter.MealIngredientsViewHolder(
            ItemIngredientBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MealIngredientsViewHolder, position: Int) {
        val ingredient = ingredients[position]
        holder.setIngredient(ingredient)
    }

    override fun getItemCount() = ingredients.size
}
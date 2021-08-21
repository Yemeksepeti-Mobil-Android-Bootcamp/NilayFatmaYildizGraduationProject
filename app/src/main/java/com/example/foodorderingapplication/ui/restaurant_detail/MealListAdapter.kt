package com.example.foodorderingapplication.ui.restaurant_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodorderingapplication.data.entity.meal.Meal
import com.example.foodorderingapplication.databinding.ItemMealBinding
import com.example.foodorderingapplication.ui.listeners.IMealClickListener

class MealListAdapter : RecyclerView.Adapter<MealListAdapter.MealViewHolder>() {

    private var mealList = ArrayList<Meal>()

    private var listener: IMealClickListener? = null

    class MealViewHolder(val binding: ItemMealBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(MealItem: Meal, listener: IMealClickListener?) {
            binding.textViewMealName.text = MealItem.name
            binding.textViewMealPrice.text = MealItem.price + " $"
            Glide.with(binding.mealImageView.context)
                .load(MealItem.image).into(binding.mealImageView)
            binding.itemMealCardView.setOnClickListener { listener?.onClick(MealItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        return MealViewHolder(
            ItemMealBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        mealList?.get(position)?.let {
            holder.bind(it, listener)
        }
    }

    override fun getItemCount(): Int {
        return mealList!!.size
    }

    fun setData(newList: ArrayList<Meal>) {
        mealList = newList
        notifyDataSetChanged()
    }

    fun setMealOnClickListener(listener: IMealClickListener) {
        this.listener = listener
    }
}
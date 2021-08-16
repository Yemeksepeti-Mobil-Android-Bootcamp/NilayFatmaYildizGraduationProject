package com.example.foodorderingapplication.ui.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderingapplication.data.entity.restaurants.RestaurantItem
import com.example.foodorderingapplication.data.entity.restaurants.Restaurants
import com.example.foodorderingapplication.databinding.ItemRestaurantBinding
import com.example.foodorderingapplication.ui.listeners.IRestaurantClickListener

class RestaurantListAdapter : RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder>() {

    var restaurantList: Restaurants? = null

    private var listener: IRestaurantClickListener? = null

    class RestaurantViewHolder(val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(RestaurantItem: RestaurantItem, listener: IRestaurantClickListener?) {
            binding.restaurantName.text = RestaurantItem.name
            binding.restaurantAddress.text = RestaurantItem.address
            binding.restaurantScore.text = RestaurantItem.score
            binding.itemRestaurantCardView.setOnClickListener { listener?.onClick(RestaurantItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(
            ItemRestaurantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        restaurantList?.get(position)?.let {
            holder.bind(it, listener)
        }
    }

    override fun getItemCount(): Int {
        return restaurantList!!.size
    }

    fun setData(newList: Restaurants?) {
        restaurantList = newList
        notifyDataSetChanged()
    }

    fun setRestaurantOnClickListener(listener: IRestaurantClickListener) {
        this.listener = listener
    }

}
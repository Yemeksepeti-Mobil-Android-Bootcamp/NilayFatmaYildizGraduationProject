package com.example.foodorderingapplication.ui.orders

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodorderingapplication.data.entity.order.Order
import com.example.foodorderingapplication.databinding.ItemOrderBinding
import java.text.SimpleDateFormat

class OrderFragmentAdapter: RecyclerView.Adapter<OrderFragmentAdapter.OrdersViewHolder>() {
    var orderList = ArrayList<Order>()

    class OrdersViewHolder(val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SimpleDateFormat")
        fun bind(order: Order) {
            binding.ordersItemRestaurantNameTextView.text = order.restaurant.name
            Glide.with(binding.ordersImageView.context)
                .load(order.meal.image).into(binding.ordersImageView)
            binding.ordersItemFoodNameTextView.text = order.meal.name
            binding.ordersItemDateTextView.text =
                SimpleDateFormat("dd/MM/yyyy").format(order.createdDate).toString()
            binding.orderPriceTextView.text = order.meal.price.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        return OrdersViewHolder(
            ItemOrderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        orderList?.get(position)?.let {
            holder.bind(it)
        }
    }

    fun setData(newList: ArrayList<Order>) {
        this.orderList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = orderList.size
}

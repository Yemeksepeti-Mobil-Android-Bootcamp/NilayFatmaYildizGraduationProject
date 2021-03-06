package com.example.foodorderingapplication.ui.restaurant_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.foodorderingapplication.data.entity.meal.Meal
import com.example.foodorderingapplication.databinding.FragmentRestaurantDetailBinding
import com.example.foodorderingapplication.ui.listeners.IMealClickListener
import com.example.foodorderingapplication.utils.Resource
import com.example.foodorderingapplication.utils.gone
import com.example.foodorderingapplication.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantDetailFragment: Fragment(){

    private lateinit var _binding: FragmentRestaurantDetailBinding
    private val viewModel: MealListViewModel by viewModels()

    private val args: RestaurantDetailFragmentArgs by navArgs()
    private val mealListAdapter = MealListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantDetailBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRestaurantDetail(args.id!!).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                    _binding.textViewMenu.gone()
                }
                Resource.Status.SUCCESS -> {
                    _binding.progressBar.gone()

                    val restaurant = it.data!!.data
                    Glide.with(_binding.imageViewRestaurant.context)
                        .load(restaurant.image).into(_binding.imageViewRestaurant)
                    _binding.textViewRestaurantName.text = restaurant.name
                    _binding.textViewRestaurantAddress.text=restaurant.district
                    _binding.textViewMenu.text = "Menu"
                    mealListAdapter.setData(restaurant.meals)
                    initViews()
                }
                Resource.Status.ERROR -> {
                    _binding.progressBar.gone()
                }
            }
        })
    }

    private fun initViews() {
        _binding.restaurantMenuRecyclerView.adapter = mealListAdapter
        _binding.restaurantMenuRecyclerView.layoutManager = LinearLayoutManager(context)
        mealListAdapter.setMealOnClickListener(object:IMealClickListener{
            override fun onClick(name: Meal) {
                val action =
                    RestaurantDetailFragmentDirections.actionRestaurantDetailFragmentToMealDetailFragment(
                        name.id,
                        args.id.toString()
                    )
                findNavController().navigate(action)
            }

        })
    }


}
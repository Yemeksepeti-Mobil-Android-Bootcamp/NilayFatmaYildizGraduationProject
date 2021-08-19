package com.example.foodorderingapplication.ui.restaurant_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapplication.data.entity.restaurant.Restaurant
import com.example.foodorderingapplication.databinding.FragmentRestaurantListBinding
import com.example.foodorderingapplication.ui.listeners.IRestaurantClickListener
import com.example.foodorderingapplication.ui.restaurant_onboarding.FirstOfferFragment
import com.example.foodorderingapplication.ui.restaurant_onboarding.RestaurantOnboardingAdapter
import com.example.foodorderingapplication.ui.restaurant_onboarding.SecondOfferFragment
import com.example.foodorderingapplication.ui.restaurant_onboarding.ThirdOfferFragment
import com.example.foodorderingapplication.utils.Resource
import com.example.foodorderingapplication.utils.gone
import com.example.foodorderingapplication.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantListFragment : Fragment() {

    private lateinit var _binding: FragmentRestaurantListBinding
    private val viewModel: RestaurantListViewModel by viewModels()

    private val restaurantListAdapter = RestaurantListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantListBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRestaurants().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    _binding.progressBar.gone()
                    viewModel.restaurantList = it.data?.restaurantList
                    setRestaurants(viewModel.restaurantList)
                    initViews()
                }
                Resource.Status.ERROR -> {
                    _binding.progressBar.gone()
                }
            }
        })
        initViewPager()
    }
    private fun setRestaurants(restaurantList: List<Restaurant>?) {
        restaurantListAdapter.setData(restaurantList)
        _binding.restaurantsRecyclerView.adapter = restaurantListAdapter
    }


    private fun initViewPager() {

        val fragmentList = arrayListOf(
            FirstOfferFragment(),
            SecondOfferFragment(),
            ThirdOfferFragment()
        )
        val adapter =
            RestaurantOnboardingAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
        _binding.apply {
            viewPager.adapter = adapter
            dotsIndicator.setViewPager2(viewPager)
        }
    }

    private fun initViews() {
        _binding.restaurantsRecyclerView.adapter = restaurantListAdapter
        _binding.restaurantsRecyclerView.layoutManager = LinearLayoutManager(context)

        restaurantListAdapter.setRestaurantOnClickListener(object : IRestaurantClickListener {
            override fun onClick(name: Restaurant) {

                Log.v("Error", "Error olmuyoooor")
                val action =
                    RestaurantListFragmentDirections.actionRestaurantListFragmentToRestaurantDetailFragment(
                       name.id
                    )
                findNavController().navigate(action)
            }
        })
    }

}
package com.example.foodorderingapplication.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapplication.R
import com.example.foodorderingapplication.data.entity.restaurants.RestaurantItem
import com.example.foodorderingapplication.databinding.FragmentRestaurantListBinding
import com.example.foodorderingapplication.databinding.FragmentSearchBinding
import com.example.foodorderingapplication.ui.listeners.IRestaurantClickListener
import com.example.foodorderingapplication.ui.restaurant_list.RestaurantListAdapter
import com.example.foodorderingapplication.ui.restaurant_list.RestaurantListFragmentDirections
import com.example.foodorderingapplication.ui.restaurant_list.RestaurantListViewModel
import com.example.foodorderingapplication.ui.restaurant_onboarding.FirstOfferFragment
import com.example.foodorderingapplication.ui.restaurant_onboarding.RestaurantOnboardingAdapter
import com.example.foodorderingapplication.ui.restaurant_onboarding.SecondOfferFragment
import com.example.foodorderingapplication.ui.restaurant_onboarding.ThirdOfferFragment
import com.example.foodorderingapplication.utils.Resource
import com.example.foodorderingapplication.utils.gone
import com.example.foodorderingapplication.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var _binding: FragmentSearchBinding
    private val viewModel: SearchListViewModel by viewModels()

    private val restaurantListAdapter = RestaurantListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var job: Job?= null
        _binding.etSearch.addTextChangedListener {editable->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                editable?.let{
                    if(editable.toString().isNotEmpty()){
                        viewModel.fetchSearchList(editable.toString()).observe(viewLifecycleOwner, {
                            when (it.status) {
                                Resource.Status.LOADING -> {
                                    _binding.progressBar.show()
                                }
                                Resource.Status.SUCCESS -> {
                                    _binding.progressBar.gone()
                                    Log.v("SearchList", "${it.data}")
                                    restaurantListAdapter.setData(it.data)
                                    initViews()

                                }
                                Resource.Status.ERROR -> {
                                    _binding.progressBar.gone()
                                }
                            }
                        })
                    }
                }
            }
        }

    }
    private fun initViews() {
        _binding.recyclerViewSearch.adapter = restaurantListAdapter
        _binding.recyclerViewSearch.layoutManager = LinearLayoutManager(context)

        restaurantListAdapter.setRestaurantOnClickListener(object : IRestaurantClickListener {
            override fun onClick(name: RestaurantItem) {

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
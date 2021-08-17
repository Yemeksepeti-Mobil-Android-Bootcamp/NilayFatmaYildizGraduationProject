package com.example.foodorderingapplication.ui.restaurant_detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapplication.R
import com.example.foodorderingapplication.data.entity.meals.MealsItem
import com.example.foodorderingapplication.data.entity.restaurants.RestaurantItem
import com.example.foodorderingapplication.databinding.FragmentRestaurantDetailBinding
import com.example.foodorderingapplication.databinding.FragmentRestaurantListBinding
import com.example.foodorderingapplication.ui.listeners.IMealClickListener
import com.example.foodorderingapplication.ui.listeners.IRestaurantClickListener
import com.example.foodorderingapplication.ui.restaurant_list.RestaurantListAdapter
import com.example.foodorderingapplication.ui.restaurant_list.RestaurantListViewModel
import com.example.foodorderingapplication.ui.restaurant_onboarding.FirstOfferFragment
import com.example.foodorderingapplication.ui.restaurant_onboarding.RestaurantOnboardingAdapter
import com.example.foodorderingapplication.ui.restaurant_onboarding.SecondOfferFragment
import com.example.foodorderingapplication.ui.restaurant_onboarding.ThirdOfferFragment
import com.example.foodorderingapplication.utils.Resource
import com.example.foodorderingapplication.utils.gone
import com.example.foodorderingapplication.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantDetailFragment : Fragment() {

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

        viewModel.fetchMealList(args.id!!).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    _binding.progressBar.gone()
                    Log.v("MealList", "${it.data}")
                    mealListAdapter.setData(it.data)
                    initViews()
                }
                Resource.Status.ERROR -> {
                    _binding.progressBar.gone()
                }
            }
        })
    }

    private fun initViews() {
        _binding.mealsRecyclerView.adapter = mealListAdapter
        _binding.mealsRecyclerView.layoutManager = LinearLayoutManager(context)

        mealListAdapter.setMealOnClickListener(object : IMealClickListener {
            override fun onClick(name: MealsItem) {

                Log.v("Error", "Error olmuyoooor")
                /*val action =
                    HospitalListFragmentDirections.actionHospitalListFragmentToHospitalDetailFragment(
                        name.name,
                        name.address
                    )
                findNavController().navigate(action)*/
            }
        })
    }

}
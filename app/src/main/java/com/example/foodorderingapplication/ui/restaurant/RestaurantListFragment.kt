package com.example.foodorderingapplication.ui.restaurant

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapplication.R
import com.example.foodorderingapplication.data.entity.restaurants.RestaurantItem
import com.example.foodorderingapplication.databinding.FragmentRestaurantListBinding
import com.example.foodorderingapplication.ui.listeners.IRestaurantClickListener
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

        viewModel.fetchRestaurantList().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    _binding.progressBar.gone()
                    Log.v("RestaurantList", "${it.data}")
                    restaurantListAdapter.setData(it.data)
                    initViews()
                }
                Resource.Status.ERROR -> {
                    _binding.progressBar.gone()
                }
            }
        })
    }

    private fun initViews() {
        _binding.restaurantsRecyclerView.adapter = restaurantListAdapter
        _binding.restaurantsRecyclerView.layoutManager = LinearLayoutManager(context)

        restaurantListAdapter.setRestaurantOnClickListener(object : IRestaurantClickListener {
            override fun onClick(name: RestaurantItem) {

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
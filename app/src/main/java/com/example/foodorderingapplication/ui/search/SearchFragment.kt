package com.example.foodorderingapplication.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapplication.data.entity.restaurant.Restaurant
import com.example.foodorderingapplication.databinding.FragmentSearchBinding
import com.example.foodorderingapplication.ui.listeners.IRestaurantClickListener
import com.example.foodorderingapplication.ui.restaurant_list.RestaurantListAdapter
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
    private val viewModel: SearchViewModel by viewModels()

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
        _binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val filterList = viewModel.searchTextOnRestaurantList(query)
                setRestaurants(filterList!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filterList = viewModel.searchTextOnRestaurantList(newText)
                setRestaurants(filterList!!)
                return true
            }

        })
        initViews()
    }
    private fun initViews() {
        _binding.recyclerViewSearch.adapter = restaurantListAdapter
        _binding.recyclerViewSearch.layoutManager = LinearLayoutManager(context)
        restaurantListAdapter.setRestaurantOnClickListener(object : IRestaurantClickListener {
            override fun onClick(name: Restaurant) {
                val action =
                    SearchFragmentDirections.actionSearchFragmentToRestaurantDetailFragment(
                        name.id
                    )
                findNavController().navigate(action)

            }
        })
    }
    private fun setRestaurants(restaurantList: List<Restaurant>) {
        isRestaurantListVisible(restaurantList.isNullOrEmpty().not())
        restaurantListAdapter.setData(restaurantList)
        _binding.recyclerViewSearch.adapter = restaurantListAdapter
    }
    private fun isRestaurantListVisible(isVisible: Boolean) {
        _binding.progressBar.gone()
        _binding.recyclerViewSearch.isVisible = isVisible

    }
}
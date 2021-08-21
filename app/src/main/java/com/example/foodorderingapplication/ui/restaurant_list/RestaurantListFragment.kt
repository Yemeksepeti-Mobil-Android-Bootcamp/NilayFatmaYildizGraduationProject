package com.example.foodorderingapplication.ui.restaurant_list

import android.app.ActionBar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapplication.R
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
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantListFragment : Fragment() {

    private lateinit var _binding: FragmentRestaurantListBinding
    private val viewModel: RestaurantListViewModel by viewModels()
    private var cuisineList: HashMap<String, ImageButton> = hashMapOf()

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

        initViewPager()
        getRestaurantList()
        setCuisineList()
        addListener()
    }

    private fun addListener() {

        restaurantListAdapter.setRestaurantOnClickListener(object : IRestaurantClickListener {
            override fun onClick(name: Restaurant) {
                val action=RestaurantListFragmentDirections.actionRestaurantListFragmentToRestaurantDetailFragment(
                    name.id
                )
                findNavController().navigate(action)
            }
        })

        _binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val filterList = viewModel.searchTextOnRestaurantList(query)
                setRestaurants(filterList)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filterList = viewModel.searchTextOnRestaurantList(newText)
                setRestaurants(filterList)
                return true
            }

        })
    }
    private fun setCuisineList() {
        val list = resources.getStringArray(R.array.Cuisines).toMutableList()
        list.add(0, getString(R.string.all_restaurants))
        val params = ActionBar.LayoutParams(
            ActionBar.LayoutParams.WRAP_CONTENT,
            ActionBar.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 80, 0)

        list.forEachIndexed { index, item ->
            val button = ImageButton(requireContext(), null, R.attr.materialButtonOutlinedStyle)
            Log.v("Items","$item")
            when(index){
                0->button.setImageResource(R.drawable.all)
                1->button.setImageResource(R.drawable.burger)
                2->button.setImageResource(R.drawable.pizza)
                3->button.setImageResource(R.drawable.pasta)
                4->button.setImageResource(R.drawable.fish)
                5->button.setImageResource(R.drawable.salad)
                6->button.setImageResource(R.drawable.dessert)
            }

            button.layoutParams = params
            _binding.cuisineTypeLinearLayout.addView(button)
            cuisineList[item] = button
        }
        addCuisineTypesListener()
    }
    private fun getRestaurantList(){
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
    }

    private fun addCuisineTypesListener() {
        cuisineList.forEach { cuisine ->
            cuisine.value.setOnClickListener {
                cuisineList.values.forEach { cuisine ->

                }
                _binding.searchView.queryHint = "Search in ${cuisine.key}"
                _binding.searchView.onActionViewCollapsed()
                if (cuisine.key == getString(R.string.all_restaurants))
                    getRestaurantList()
                else
                    sendCuisineRequest(cuisine.key)
            }
        }
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

    private fun sendCuisineRequest(cuisineName: String) {
        viewModel.getRestaurantByCuisine(cuisineName).observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> _binding.progressBar.show()
                Resource.Status.SUCCESS -> {
                    _binding.progressBar.gone()
                    viewModel.restaurantList = response.data?.restaurantList
                    setRestaurants(response.data?.restaurantList)
                }
                Resource.Status.ERROR -> isRestaurantListVisible(false)
            }
        })
    }
    private fun isRestaurantListVisible(isVisible: Boolean) {
        _binding.progressBar.gone()
        _binding.restaurantsRecyclerView.isVisible = isVisible
        _binding.responseErrorLinearLayout.isVisible = isVisible.not()
    }

    private fun initViews() {
        _binding.restaurantsRecyclerView.adapter = restaurantListAdapter
        _binding.restaurantsRecyclerView.layoutManager = LinearLayoutManager(context)

        restaurantListAdapter.setRestaurantOnClickListener(object : IRestaurantClickListener {
            override fun onClick(name: Restaurant) {
                val action =
                    RestaurantListFragmentDirections.actionRestaurantListFragmentToRestaurantDetailFragment(
                        name.id
                    )
                findNavController().navigate(action)
            }
        })
    }


}
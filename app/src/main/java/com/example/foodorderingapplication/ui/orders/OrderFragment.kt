package com.example.foodorderingapplication.ui.orders

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapplication.R
import com.example.foodorderingapplication.databinding.FragmentOrderBinding
import com.example.foodorderingapplication.utils.Resource
import com.example.foodorderingapplication.utils.gone
import com.example.foodorderingapplication.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : Fragment() {

    private var binding: FragmentOrderBinding? = null
    private val viewModel: OrderFragmentViewModel by viewModels()
    val adapter = OrderFragmentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getOrders()
    }

    private fun getOrders() {
        viewModel.getOrders().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding?.progressBar?.show()
                }
                Resource.Status.SUCCESS -> {
                    it.data?.orderList?.let {
                        binding?.orderRecyclerView?.layoutManager = LinearLayoutManager(context)
                        binding?.orderRecyclerView?.adapter = adapter
                        adapter.setData(it)
                        binding?.progressBar?.gone()
                    }

                }

                Resource.Status.ERROR -> {
                    binding?.progressBar?.gone()
                }
            }
        })
    }

}
package com.example.foodorderingapplication.ui.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.foodorderingapplication.databinding.FragmentRegisterBinding
import com.example.foodorderingapplication.ui.MainActivity
import com.example.foodorderingapplication.utils.Resource
import com.example.foodorderingapplication.utils.gone
import com.example.foodorderingapplication.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var _binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.buttonRegister.setOnClickListener {

            val name = _binding.registerNameTextView.text.toString()
            val email = _binding.registerEmailTextView.text.toString()
            val password = _binding.registerPasswordTextView.text.toString()

            viewModel.register(name, email, password)
                .observe(viewLifecycleOwner, Observer {
                    when (it.status) {
                        Resource.Status.LOADING -> {
                        }
                        Resource.Status.SUCCESS -> {

                            val action =
                                RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                            findNavController().navigate(action)

                            }

                        Resource.Status.ERROR -> {
                            Log.v("Error","Error")
                        }
                        }

                    })
        }
        _binding.textViewHaveAnAccount.setOnClickListener {
            val action =
                RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }

}
package com.example.foodorderingapplication.ui.login

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.foodorderingapplication.databinding.FragmentLoginBinding
import com.example.foodorderingapplication.utils.Resource
import com.example.foodorderingapplication.utils.gone
import com.example.foodorderingapplication.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var _binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.buttonLogin.setOnClickListener {
            val email = _binding.loginEmailTextView.text.toString()
            val password = _binding.loginPasswordTextView.text.toString()
            viewModel.login(email, password).observe(viewLifecycleOwner, Observer {
                when (it.status) {

                    Resource.Status.LOADING -> {
                        _binding.progressBar.gone()
                    }
                    Resource.Status.SUCCESS -> {
                        _binding.progressBar.show()
                            val action=LoginFragmentDirections.actionLoginFragmentToRestaurantListFragment()
                            findNavController().navigate(action)
                        }
                    Resource.Status.ERROR -> {
                        _binding.progressBar.gone()
                        val dialog = AlertDialog.Builder(context)
                            .setTitle("Error")
                            .setMessage("${it.message}")
                            .setPositiveButton("ok") { dialog, button ->
                                dialog.dismiss()
                            }
                        dialog.show()
                    }
                }
            })


        }
        _binding.buttonRegister.setOnClickListener {
            val action=LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }
    }

}
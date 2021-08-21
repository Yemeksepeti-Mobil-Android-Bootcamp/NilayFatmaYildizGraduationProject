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
                    }
                    Resource.Status.SUCCESS -> {
                        viewGones()
                            val action=LoginFragmentDirections.actionLoginFragmentToRestaurantListFragment()
                            findNavController().navigate(action)
                        }
                    Resource.Status.ERROR -> {
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

    private fun viewGones(){
        _binding.itemLoginCardView.visibility = View.GONE
        _binding.loginIcon.visibility = View.GONE
        _binding.loginEmailTextView.visibility = View.GONE
        _binding.textSignIn.visibility=View.GONE
        _binding.textViewDontHaveAnAccount.visibility=View.GONE
        _binding.textViewEnterAccount.visibility=View.GONE
        _binding.loginPasswordTextView.visibility = View.GONE
        _binding.buttonRegister.visibility=View.GONE
        _binding.buttonLogin.visibility = View.GONE
    }
}
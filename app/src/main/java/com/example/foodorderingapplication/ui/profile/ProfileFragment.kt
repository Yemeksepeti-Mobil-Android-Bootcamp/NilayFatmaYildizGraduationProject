package com.example.foodorderingapplication.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.foodorderingapplication.data.entity.User
import com.example.foodorderingapplication.databinding.FragmentProfileBinding
import com.example.foodorderingapplication.utils.Resource
import com.example.foodorderingapplication.utils.gone
import com.example.foodorderingapplication.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var _binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.progressBar.show()
        addListeners()
        getProfile()
    }

    private fun getProfile() {
        viewModel.getUser().observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    setLoading(true)

                }
                Resource.Status.SUCCESS -> {
                    setLoading(false)
                    setInformations(response.data?.user)
                }
                Resource.Status.ERROR -> {
                    setLoading(false)
                    Toast.makeText(context, "Operation Failed", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setInformations(user: User?) {
        _binding.userNameTextView.text = user?.name
        _binding.userEmailTextView.text = user?.email
        _binding.userPhoneNumberTextView.text = user?.phone
        _binding.userAddressTextView.text = user?.address
    }

    private fun addListeners() {

        _binding.buttonLogout.setOnClickListener {
            viewModel.logOut()
            val action=ProfileFragmentDirections.actionProfileFragmentToLoginFragment()
            findNavController().navigate(action)
        }

        _binding.buttonChangeProfile.setOnClickListener {
            val action= ProfileFragmentDirections.actionProfileFragmentToSettingFragment()
            findNavController().navigate(action)
        }
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            _binding?.progressBar?.show()
            _binding?.buttonLogout?.gone()
            _binding?.lineViewEmail?.gone()
            _binding?.lineViewAddress?.gone()
            _binding?.lineViewPhoneNumber?.gone()
        } else {
            _binding?.progressBar?.gone()
            _binding?.buttonLogout?.show()
            _binding?.lineViewEmail?.show()
            _binding?.lineViewAddress?.show()
            _binding?.lineViewPhoneNumber?.show()
        }
    }

}
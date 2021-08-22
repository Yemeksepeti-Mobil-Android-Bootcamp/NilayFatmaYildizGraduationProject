package com.example.foodorderingapplication.ui.settings

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.foodorderingapplication.data.entity.User
import com.example.foodorderingapplication.data.entity.profile.UserRequest
import com.example.foodorderingapplication.databinding.FragmentSettingBinding
import com.example.foodorderingapplication.utils.Resource
import com.example.foodorderingapplication.utils.gone
import com.example.foodorderingapplication.utils.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingFragment : Fragment() {
    private lateinit var _binding: FragmentSettingBinding
    private val viewModel: CompleteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return _binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        addListeners()
    }

    private fun initViews() {
        viewModel.getUser().observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    setField(response.data?.user)
                    isSettingVisible(true)
                }
                Resource.Status.ERROR -> {
                    isSettingVisible(false)
                }
            }
        })
    }

    private fun setField(user: User?) {
        _binding.textViewUpdateName.setText(user?.name)
        _binding.textViewUpdateEmail.setText(user?.email)
        _binding.textViewUpdateAddress.setText(user?.address)
        _binding.textViewUpdatePhoneNumber.setText(user?.phone)

    }

    private fun addListeners() {
        _binding.buttonUpdate.setOnClickListener { updateUser() }
    }


    private fun updateUser() {
        val name = _binding.textViewUpdateName.text.toString()
        val mail = _binding.textViewUpdateEmail.text.toString()
        val phone = _binding.textViewUpdatePhoneNumber.text.toString()
        val address = _binding.textViewUpdateAddress.text.toString()

        val user = UserRequest(mail, name, address, phone)
        viewModel.updateUser(user).observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    val action = SettingFragmentDirections.actionSettingFragmentToProfileFragment()
                    findNavController().navigate(action)
                    isSettingVisible(true)
                }
                Resource.Status.ERROR -> {
                    isSettingVisible(false)
                }
            }
        })
    }

    private fun isSettingVisible(isVisible: Boolean) {
        _binding.progressBar.gone()
        if (isVisible.not()) {
            AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage("There is a problem")
                .setPositiveButton("Cancel") { _, _ ->
                    findNavController().popBackStack()
                }.show()
        }
    }
}
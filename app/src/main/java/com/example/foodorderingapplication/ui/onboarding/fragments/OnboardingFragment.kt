package com.example.foodorderingapplication.ui.onboarding.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.foodorderingapplication.R
import com.example.foodorderingapplication.databinding.FragmentOnboardingBinding
import com.example.foodorderingapplication.ui.onboarding.utils.ViewPagerAdapter
import com.example.foodorderingapplication.ui.onboarding.utils.ZoomOutPageTransformer

class OnboardingFragment : Fragment() {
    private lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val adapter = ViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = adapter
        binding.viewPager.setPageTransformer(ZoomOutPageTransformer())
        binding.dotsIndicator.setViewPager2(binding.viewPager)

        binding.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    binding.prevButton.visibility = View.GONE
                    binding.nextButton.setOnClickListener {
                        binding.viewPager.currentItem =
                            binding.viewPager.currentItem + 1
                    }
                } else if (position == 2) {
                    binding.prevButton.visibility = View.VISIBLE
                    binding.nextButton.text = resources.getText(R.string.finish)
                    binding.nextButton.setOnClickListener {
                        //val action =
                            //OnboardingFragmentDirections.actionOnboardingFragmentToHomeFragment()
                        //findNavController().navigate(action)
                        onBoardingFinished()
                    }
                } else {
                    binding.prevButton.visibility = View.VISIBLE
                    binding.nextButton.text = resources.getText(R.string.next)
                    binding.nextButton.setOnClickListener {
                        binding.viewPager.currentItem =
                            binding.viewPager.currentItem + 1
                    }
                    binding.prevButton.setOnClickListener {
                        binding.viewPager.currentItem =
                            binding.viewPager.currentItem - 1
                    }
                }
            }
        })

    }
    private fun onBoardingFinished(){
        val sharedPref=requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor =sharedPref.edit()
        editor.putBoolean("Finished",true)
        editor.apply()
    }

}
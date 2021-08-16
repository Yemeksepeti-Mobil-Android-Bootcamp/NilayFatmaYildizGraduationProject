package com.example.foodorderingapplication.ui.onboarding.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.foodorderingapplication.ui.onboarding.fragments.FirstOnboardingFragment
import com.example.foodorderingapplication.ui.onboarding.fragments.SecondOnboardingFragment
import com.example.foodorderingapplication.ui.onboarding.fragments.ThirdOnboardingFragment

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> FirstOnboardingFragment()
            1 -> SecondOnboardingFragment()
            else -> ThirdOnboardingFragment()
        }
    }
}

package com.afurkantitiz.newsapp.ui.onboard.onboardscreen

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.afurkantitiz.newsapp.R
import com.afurkantitiz.newsapp.base.BaseFragment
import com.afurkantitiz.newsapp.databinding.FragmentFirstOnboardBinding

class FirstOnboardFragment :
    BaseFragment<FragmentFirstOnboardBinding>(FragmentFirstOnboardBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val onboardViewPager = activity?.findViewById<ViewPager2>(R.id.onboardViewPager)

        binding.firstOnboardNextButton.setOnClickListener {
            onboardViewPager?.currentItem = 1
        }
    }
}
package com.afurkantitiz.newsapp.ui.onboard

import android.os.Bundle
import android.view.View
import com.afurkantitiz.newsapp.base.BaseFragment
import com.afurkantitiz.newsapp.databinding.FragmentOnboardControllerBinding
import com.afurkantitiz.newsapp.ui.onboard.animation.DepthTransformation

class OnboardControllerFragment :
    BaseFragment<FragmentOnboardControllerBinding>(FragmentOnboardControllerBinding::inflate) {
    private lateinit var adapter: OnboardAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = OnboardAdapter(requireActivity())
        binding.onboardViewPager.setPageTransformer(DepthTransformation())
        binding.onboardViewPager.adapter = adapter
    }

    override fun isNavigationBarVisible(): Boolean = false
}
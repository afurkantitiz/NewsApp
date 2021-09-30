package com.afurkantitiz.newsapp.ui.onboard.onboardscreen

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.afurkantitiz.newsapp.R
import com.afurkantitiz.newsapp.base.BaseFragment
import com.afurkantitiz.newsapp.data.local.SharedPrefManager
import com.afurkantitiz.newsapp.databinding.FragmentSecondOnboardBinding

class SecondOnboardFragment :
    BaseFragment<FragmentSecondOnboardBinding>(FragmentSecondOnboardBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.secondOnboardFinishButton.setOnClickListener {
            SharedPrefManager(requireContext()).setOnboardSeen()
            findNavController().navigate(R.id.action_onboardControllerFragment_to_newsFragment)
        }
    }
}
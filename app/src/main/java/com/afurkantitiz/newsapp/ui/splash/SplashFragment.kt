package com.afurkantitiz.newsapp.ui.splash

import android.animation.Animator
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.afurkantitiz.newsapp.R
import com.afurkantitiz.newsapp.base.BaseFragment
import com.afurkantitiz.newsapp.data.local.SharedPrefManager
import com.afurkantitiz.newsapp.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lottieListener()
    }

    private fun lottieListener() {
        binding.lottieAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
                Log.v("Animation", "Started")
            }

            override fun onAnimationEnd(animation: Animator?) {
                if (isOnboardSeen())
                    findNavController().navigate(R.id.action_splashFragment_to_newsFragment)
                else
                    findNavController().navigate(R.id.action_splashFragment_to_onboardControllerFragment)
            }

            override fun onAnimationCancel(animation: Animator?) {
                Log.v("Animation", "Canceled")
            }

            override fun onAnimationRepeat(animation: Animator?) {
                Log.v("Animation", "Repeated")
            }
        })
    }

    private fun isOnboardSeen(): Boolean = SharedPrefManager(requireContext()).isOnboardSeen()

    override fun isNavigationBarVisible(): Boolean = false
}
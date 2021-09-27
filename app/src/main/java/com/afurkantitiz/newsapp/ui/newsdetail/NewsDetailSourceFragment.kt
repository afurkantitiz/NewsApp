package com.afurkantitiz.newsapp.ui.newsdetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.afurkantitiz.newsapp.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import com.afurkantitiz.newsapp.databinding.FragmentNewsDetailSourceBinding

@AndroidEntryPoint
class NewsDetailSourceFragment :
    BaseFragment<FragmentNewsDetailSourceBinding>(FragmentNewsDetailSourceBinding::inflate) {
    private val args: NewsDetailSourceFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        onClickListener()
    }

    private fun onClickListener() {
        binding.backButtonWebView.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initViews() {
        binding.apply {
            newsDetailWebView.settings.javaScriptEnabled = true

            args.currentSourceUrl.let {
                newsDetailWebView.loadUrl(it)
            }
        }
    }

    override fun isNavigationBarVisible(): Boolean = false
}
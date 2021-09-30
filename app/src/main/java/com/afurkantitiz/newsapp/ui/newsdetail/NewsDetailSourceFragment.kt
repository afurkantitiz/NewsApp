package com.afurkantitiz.newsapp.ui.newsdetail

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.afurkantitiz.newsapp.base.BaseFragment
import com.afurkantitiz.newsapp.databinding.FragmentNewsDetailSourceBinding
import com.afurkantitiz.newsapp.utils.gone
import com.afurkantitiz.newsapp.utils.show
import dagger.hilt.android.AndroidEntryPoint

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
            binding.newsDetailWebView.webViewClient = WebViewClient()

            args.currentSourceUrl.let {
                newsDetailWebView.loadUrl(it)
            }
        }
    }

    override fun isNavigationBarVisible(): Boolean = false

    inner class WebViewClient : android.webkit.WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            binding.progressBar.gone()
            binding.newsDetailWebView.show()
        }
    }
}
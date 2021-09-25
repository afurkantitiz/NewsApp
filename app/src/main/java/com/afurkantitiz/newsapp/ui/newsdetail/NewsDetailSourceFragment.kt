package com.afurkantitiz.newsapp.ui.newsdetail

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.afurkantitiz.newsapp.databinding.FragmentNewsDetailSourceBinding
import com.afurkantitiz.newsapp.utils.gone
import com.afurkantitiz.newsapp.utils.show

class NewsDetailSourceFragment : Fragment() {
    private var _binding: FragmentNewsDetailSourceBinding? = null
    private val binding get() = _binding!!

    private val args: NewsDetailSourceFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsDetailSourceBinding.inflate(inflater, container, false)
        return binding.root
    }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
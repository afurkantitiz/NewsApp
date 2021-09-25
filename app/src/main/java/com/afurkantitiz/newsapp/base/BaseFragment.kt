package com.afurkantitiz.newsapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.afurkantitiz.newsapp.R
import com.afurkantitiz.newsapp.ui.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment(), IBottomBarInterface {

    private var _binding: VB? = null
    val binding get() = _binding!!

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        if (isNavigationBarVisible())
            (activity as MainActivity).showNavigationBar()
        else
            (activity as MainActivity).hideNavigationBar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
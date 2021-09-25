package com.afurkantitiz.newsapp.ui.favorite

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.afurkantitiz.newsapp.base.BaseFragment
import com.afurkantitiz.newsapp.data.entitiy.ArticleRoom
import com.afurkantitiz.newsapp.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate) {
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoriteNewsList = viewModel.getFavoriteNews() as ArrayList<ArticleRoom>
        Log.v("favoriteList", "${favoriteNewsList.size}")
    }
}
package com.afurkantitiz.newsapp.ui.favorite

import androidx.lifecycle.ViewModel
import com.afurkantitiz.newsapp.data.ApiRepository
import com.afurkantitiz.newsapp.data.entitiy.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private var apiRepository: ApiRepository
): ViewModel() {
    fun getFavoriteNews(): List<Article>{
        return apiRepository.getFavoriteNews()
    }

    fun unFavouriteNews(news: Article){
        return apiRepository.unFavorite(news)
    }
}
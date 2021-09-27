package com.afurkantitiz.newsapp.ui.favorite

import androidx.lifecycle.ViewModel
import com.afurkantitiz.newsapp.data.ApiRepository
import com.afurkantitiz.newsapp.data.entitiy.ArticleRoom
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private var apiRepository: ApiRepository
): ViewModel() {
    fun getFavoriteNews(): List<ArticleRoom>{
        return apiRepository.getFavoriteNews()
    }

    fun unFavouriteNews(news: ArticleRoom){
        return apiRepository.unFavorite(news)
    }
}
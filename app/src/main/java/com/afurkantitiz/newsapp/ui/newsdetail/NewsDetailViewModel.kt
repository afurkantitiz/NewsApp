package com.afurkantitiz.newsapp.ui.newsdetail

import androidx.lifecycle.ViewModel
import com.afurkantitiz.newsapp.data.ApiRepository
import com.afurkantitiz.newsapp.data.entitiy.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private var apiRepository: ApiRepository
) : ViewModel() {
    fun addFavorite(articleRoom: Article){
        apiRepository.addFavorite(articleRoom)
    }
}
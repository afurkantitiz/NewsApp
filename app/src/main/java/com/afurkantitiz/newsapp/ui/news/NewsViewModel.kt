package com.afurkantitiz.newsapp.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.afurkantitiz.newsapp.data.ApiRepository
import com.afurkantitiz.newsapp.data.entitiy.Article
import com.afurkantitiz.newsapp.data.entitiy.NewsResponse
import com.afurkantitiz.newsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    var apiRepository: ApiRepository
) : ViewModel() {

    fun getNewsByQuery(query: String, page: Int): LiveData<Resource<NewsResponse>>{
        return apiRepository.getNewsByQuery(query, page)
    }
}
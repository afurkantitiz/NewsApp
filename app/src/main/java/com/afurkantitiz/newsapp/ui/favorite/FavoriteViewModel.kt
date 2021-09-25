package com.afurkantitiz.newsapp.ui.favorite

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.afurkantitiz.newsapp.data.ApiRepository
import com.afurkantitiz.newsapp.data.entitiy.ArticleRoom
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
): ViewModel() {
    var itemList = ArrayList<ArticleRoom>()

    fun getFavoriteNews(): List<ArticleRoom>{
        return apiRepository.getFavoriteNews()
    }
}
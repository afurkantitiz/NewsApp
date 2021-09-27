package com.afurkantitiz.newsapp.ui.favorite

import com.afurkantitiz.newsapp.data.entitiy.Article

interface IUnFavouriteItem {
    fun unFavouriteItem(articleRoom: Article, position: Int)
}
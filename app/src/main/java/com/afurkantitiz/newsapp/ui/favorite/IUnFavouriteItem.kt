package com.afurkantitiz.newsapp.ui.favorite

import com.afurkantitiz.newsapp.data.entitiy.ArticleRoom

interface IUnFavouriteItem {
    fun unFavouriteItem(articleRoom: ArticleRoom, position: Int)
}
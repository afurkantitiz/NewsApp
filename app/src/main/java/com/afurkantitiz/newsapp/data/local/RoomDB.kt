package com.afurkantitiz.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.afurkantitiz.newsapp.data.entitiy.Article
import com.afurkantitiz.newsapp.utils.DateTypeConverters

@TypeConverters(value = [DateTypeConverters::class])
@Database(entities = [Article::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
package com.afurkantitiz.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.afurkantitiz.newsapp.data.entitiy.ArticleRoom
import com.afurkantitiz.newsapp.utils.Converters
import com.afurkantitiz.newsapp.utils.DateTypeConverters

@TypeConverters(value = [Converters::class, DateTypeConverters::class])
@Database(entities = [ArticleRoom::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
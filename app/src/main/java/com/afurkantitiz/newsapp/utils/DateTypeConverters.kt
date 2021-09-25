package com.afurkantitiz.newsapp.utils

import androidx.room.TypeConverter
import java.util.*

class DateTypeConverters {
    @TypeConverter
    fun fromTimestamp(value: Long): Date{
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long{
        return date.time
    }
}
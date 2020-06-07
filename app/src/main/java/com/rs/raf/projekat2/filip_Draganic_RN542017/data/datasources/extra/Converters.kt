package com.rs.raf.projekat2.filip_Draganic_RN542017.data.datasources.extra

import androidx.room.TypeConverter
import java.util.*

class Converters {
    init {

    }
    @TypeConverter
    fun fromTimestamp(value: Long): Date {
       return if (value == null) Date() else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }
}
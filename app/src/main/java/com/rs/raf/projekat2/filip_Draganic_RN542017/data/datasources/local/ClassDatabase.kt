package com.rs.raf.projekat2.filip_Draganic_RN542017.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.ClassEntity

@Database(
    entities = [ClassEntity::class],
    version = 2,
    exportSchema = false)
@TypeConverters()
abstract class ClassDatabase : RoomDatabase() {
    abstract fun getClassDao(): ClassDao
}
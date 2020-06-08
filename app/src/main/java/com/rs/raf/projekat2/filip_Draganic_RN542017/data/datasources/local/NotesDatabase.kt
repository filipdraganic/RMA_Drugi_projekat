package com.rs.raf.projekat2.filip_Draganic_RN542017.data.datasources.local

import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.datasources.extra.Converters
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.NotesEntity
import java.util.*

@Database(
    entities = [NotesEntity::class],
    version = 2,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class NotesDatabase : RoomDatabase(){
    abstract fun getNotesDao(): NotesDao

}




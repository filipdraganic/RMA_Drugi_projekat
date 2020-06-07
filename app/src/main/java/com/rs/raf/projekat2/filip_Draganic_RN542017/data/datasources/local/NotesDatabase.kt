package com.rs.raf.projekat2.filip_Draganic_RN542017.data.datasources.local

import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.TypeConverters
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.NotesEntity

@Database(
    entities = [NotesEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters
abstract class NotesDatabase : RoomDatabase(){
    abstract fun getNotesDao(): NotesDao

}
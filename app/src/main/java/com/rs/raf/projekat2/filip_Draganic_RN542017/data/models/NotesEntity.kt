package com.rs.raf.projekat2.filip_Draganic_RN542017.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
data class NotesEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val creator: Long,
    val dateCreated: Long,
    val title: String,
    val content: String,
    val isArchived: Int
)
package com.rs.raf.projekat2.filip_Draganic_RN542017.data.models

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "classes")
data class ClassEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,
    val predmet: String,
    val nastavnik: String,
    val tip: String,
    val dan: String,
    val termin: String,
    val ucionica: String,
    val grupe: String

)

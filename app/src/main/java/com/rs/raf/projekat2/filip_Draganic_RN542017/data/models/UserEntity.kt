package com.rs.raf.projekat2.filip_Draganic_RN542017.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val username: String,
    val password: String
)
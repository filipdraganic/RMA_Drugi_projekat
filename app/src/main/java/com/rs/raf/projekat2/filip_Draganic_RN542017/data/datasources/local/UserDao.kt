package com.rs.raf.projekat2.filip_Draganic_RN542017.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.NotesEntity
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.UserEntity
import io.reactivex.Completable
import retrofit2.http.GET

@Dao
abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: UserEntity): Completable

    @Query("SELECT * FROM users WHERE (username LIKE :username AND password LIKE :password)")
    abstract fun getByLogin(username :String, password :String)

}
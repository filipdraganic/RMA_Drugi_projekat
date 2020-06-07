package com.rs.raf.projekat2.filip_Draganic_RN542017.data.datasources.local

import androidx.room.*
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.NotesEntity
import io.reactivex.Completable
import io.reactivex.Observable


@Dao
abstract class NotesDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: NotesEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(entities: List<NotesEntity>): Completable

    @Query("SELECT * FROM notes")
    abstract fun getAll(): Observable<List<NotesEntity>>

    @Query("DELETE FROM notes")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<NotesEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM notes WHERE (title LIKE :searchQuery || '%' OR content LIKE '%' || :searchQuery || '%') AND (creator LIKE :creator) AND (isArchived LIKE :isArchived OR isArchived LIKE 'TRUE')")
    abstract fun getNotesByFilter(searchQuery: String, creator: Long, isArchived: Boolean): Observable<List<NotesEntity>>


}
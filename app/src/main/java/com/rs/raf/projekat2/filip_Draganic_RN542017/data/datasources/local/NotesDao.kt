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

    @Query("SELECT * FROM notes ORDER BY dateCreated")
    abstract fun getAll(): Observable<List<NotesEntity>>

    @Query("DELETE FROM notes")
    abstract fun deleteAll()

    @Query("DELETE FROM notes WHERE id = :id")
    abstract fun delete(id: Long):Completable

    @Transaction
    open fun deleteAndInsertAll(entities: List<NotesEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM notes WHERE (title LIKE :searchQuery || '%' OR content LIKE '%' || :searchQuery || '%') AND (creator LIKE :creator) AND (isArchived LIKE :isArchived OR isArchived LIKE 0)")
    abstract fun getNotesByFilter(searchQuery: String, creator: Long, isArchived: Int): Observable<List<NotesEntity>>

    @Query("UPDATE notes SET isArchived = :archive WHERE id LIKE :id")
    abstract fun archive(id: Long, archive: Int):Completable

    @Query("UPDATE notes SET title = :title , content = :content WHERE id LIKE :id")
    abstract fun update(id: Long, title: String, content: String):Completable


}
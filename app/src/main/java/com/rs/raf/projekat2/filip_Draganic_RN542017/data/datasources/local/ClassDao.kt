package com.rs.raf.projekat2.filip_Draganic_RN542017.data.datasources.local

import androidx.room.*
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.ClassEntity
import io.reactivex.Completable
import io.reactivex.Observable


@Dao
abstract class ClassDao{

    @Query("SELECT * FROM classes")
    abstract fun getAll(): Observable<List<ClassEntity>>

    @Query("SELECT * FROM classes WHERE (grupe LIKE '%' || :grupe || '%' OR :grupe LIKE 'Grupa') AND (dan LIKE  '%' || :dan || '%' OR :dan LIKE 'Dan')  AND  (nastavnik LIKE '%' || :searchQuery || '%' OR predmet LIKE '%' || :searchQuery || '%')")
    abstract fun getClassByFilter(grupe :String, dan :String, searchQuery: String): Observable<List<ClassEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: ClassEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(entities: List<ClassEntity>): Completable

    @Query("DELETE FROM classes")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<ClassEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

}
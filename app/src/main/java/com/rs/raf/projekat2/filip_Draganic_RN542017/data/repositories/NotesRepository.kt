package com.rs.raf.projekat2.filip_Draganic_RN542017.data.repositories

import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.*
import io.reactivex.Completable
import io.reactivex.Observable

interface NotesRepository{

    fun fetchAll(): Observable<Resource<Unit>>
    fun getAll(): Observable<List<Notes>>
    fun insert(note: Notes): Completable
    fun getNotesByFilter(notesFilter: NotesFilter): Observable<List<Notes>>
    fun update(title: String, content: String): Completable
    fun archive(id: Long, archive:Int):Completable
    fun delete(id: Long):Completable
    fun update(id: Long, title: String, content: String): Completable
}
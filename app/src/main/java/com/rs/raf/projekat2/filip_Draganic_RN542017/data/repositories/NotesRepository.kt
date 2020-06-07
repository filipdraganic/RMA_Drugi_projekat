package com.rs.raf.projekat2.filip_Draganic_RN542017.data.repositories

import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.*
import io.reactivex.Completable
import io.reactivex.Observable

interface NotesRepository{

    fun fetchAll(): Observable<Resource<Unit>>
    fun getAll(): Observable<List<Notes>>
    fun insert(note: Notes): Completable
    fun getNotesByFilter(notesFilter: NotesFilter): Observable<List<Notes>>

}
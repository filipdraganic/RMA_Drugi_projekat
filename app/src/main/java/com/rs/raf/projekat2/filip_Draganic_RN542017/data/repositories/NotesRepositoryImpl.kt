package com.rs.raf.projekat2.filip_Draganic_RN542017.data.repositories

import com.rs.raf.projekat2.filip_Draganic_RN542017.data.datasources.local.NotesDao
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Class
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Notes
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.NotesFilter
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Resource
import io.reactivex.Completable
import io.reactivex.Observable

class NotesRepositoryImpl(private val localDataSource: NotesDao) :NotesRepository{


    override fun fetchAll(): Observable<Resource<Unit>> {
        TODO("Not yet implemented")
    }

    override fun getAll(): Observable<List<Notes>> {
        TODO("Not yet implemented")
    }

    override fun insert(note: Notes): Completable {
        TODO("Not yet implemented")
    }


    override fun getNotesByFilter(notesFilter: NotesFilter): Observable<List<Notes>> {
        TODO("Not yet implemented")
    }


}
package com.rs.raf.projekat2.filip_Draganic_RN542017.data.repositories

import com.rs.raf.projekat2.filip_Draganic_RN542017.data.datasources.extra.Converters
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.datasources.local.NotesDao
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.*
import io.reactivex.Completable
import io.reactivex.Observable
import timber.log.Timber
import java.util.*

class NotesRepositoryImpl(private val localDataSource: NotesDao) :NotesRepository{


    override fun fetchAll(): Observable<Resource<Unit>> {
        TODO("Not yet implemented")
    }

    override fun getAll(): Observable<List<Notes>> {
        Timber.e("GLEDAJ OVDE -> Get all u NotesRepoImpl" )

        return localDataSource
            .getAll()
            .map {
                it.map {
                    Notes(it.id, it.creator, Converters().fromTimestamp(it.dateCreated), it.title, it.content, it.isArchived)
                }
            }
    }

    override fun insert(note: Notes): Completable {
        Timber.e("NotesRepoImplementacija ?")
        val noteEntity = NotesEntity(note.id, 1, Converters().dateToTimestamp(note.dateCreated), note.title, note.content, note.isArchived)
        return localDataSource
            .insert(noteEntity)

    }


    override fun getNotesByFilter(notesFilter: NotesFilter): Observable<List<Notes>> {
        Timber.e("GLEDAJ OVDE ->" + notesFilter.toString())

        return localDataSource
            .getNotesByFilter(notesFilter.searchQuery, notesFilter.creator, notesFilter.isArchived)
            .map {
                it.map {
                    Notes(it.id, it.creator, Converters().fromTimestamp(it.dateCreated), it.title, it.content, it.isArchived)
                }
            }

    }

    override fun update(title: String, content: String) {
        TODO("Not yet implemented")
    }

    override fun archive() {
        TODO("Not yet implemented")
    }


}
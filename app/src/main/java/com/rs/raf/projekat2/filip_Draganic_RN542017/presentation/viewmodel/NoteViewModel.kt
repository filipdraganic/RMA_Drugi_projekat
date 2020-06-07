package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.NotesFilter
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.repositories.NotesRepository
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.contract.NotesContract
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.states.NotesState
import io.reactivex.disposables.CompositeDisposable

class NoteViewModel(private val notesRepository: NotesRepository): ViewModel(), NotesContract.ViewModel{

    private val subscriptions = CompositeDisposable()

    override val notesState : MutableLiveData<NotesState> = MutableLiveData()



    override fun fetchAll() {
        TODO("Not yet implemented")
    }

    override fun getAll() {
        TODO("Not yet implemented")
    }

    override fun getNotesByFilter(notesFilter: NotesFilter) {
        TODO("Not yet implemented")
    }

}
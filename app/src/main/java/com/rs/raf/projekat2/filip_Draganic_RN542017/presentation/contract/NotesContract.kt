package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.contract

import androidx.lifecycle.LiveData
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.NotesFilter
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.states.NotesState

interface NotesContract{

    interface ViewModel{

        val notesState: LiveData<NotesState>

        fun fetchAll()
        fun getAll()
        fun getNotesByFilter(notesFilter: NotesFilter)
    }

}
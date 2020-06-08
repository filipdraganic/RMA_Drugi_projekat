package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.contract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Notes
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.NotesFilter
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.states.AddNotesState
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.states.NotesState

interface NotesContract{

    interface ViewModel{

        val notesState: LiveData<NotesState>
        val addDone: LiveData<AddNotesState>
        val drawSet: MutableLiveData<List<Int>>

        fun fetchAll()
        fun getAll()
        fun getNotesByFilter(notesFilter: NotesFilter)
        fun insert(note: Notes)
        fun update(id: Long, title: String, content: String)
        fun archive(id: Long, archive: Int)
        fun delete(id: Long)
        fun drawSetData()
    }

}
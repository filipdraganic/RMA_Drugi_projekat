package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.states

import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Notes

sealed class NotesState{
    object Loading: NotesState()
    object DataFetched: NotesState()
    object Update: NotesState()
    object Delete: NotesState()
    object Edit: NotesState()

    data class Success(val notes: List<Notes>): NotesState()
    data class Error(val message: String): NotesState()



}
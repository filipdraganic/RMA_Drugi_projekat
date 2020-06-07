package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.states

import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Class
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Notes

sealed class NotesState{
    object Loading: NotesState()
    object DataFetched: NotesState()
    data class Success(val classes: List<Notes>): NotesState()
    data class Error(val message: String): NotesState()
}
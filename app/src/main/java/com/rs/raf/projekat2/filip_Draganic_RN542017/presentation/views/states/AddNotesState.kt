package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.states

sealed class AddNotesState {
    object Success: AddNotesState()
    data class Error(val message: String): AddNotesState()
}
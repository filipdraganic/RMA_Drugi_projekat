package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.states

sealed class AddClassState{
    object Success: AddClassState()
    data class Error(val message: String):AddClassState()
}
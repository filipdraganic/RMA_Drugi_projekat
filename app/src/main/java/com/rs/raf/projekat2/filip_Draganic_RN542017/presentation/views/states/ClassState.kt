package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.states

import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Class

sealed class ClassState{
    object Loading: ClassState()
    object DataFetched: ClassState()
    data class Success(val classes: List<Class>): ClassState()
    data class Error(val message: String): ClassState()

}
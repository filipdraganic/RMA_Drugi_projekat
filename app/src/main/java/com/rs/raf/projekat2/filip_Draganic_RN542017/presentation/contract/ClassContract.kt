package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.contract

import androidx.lifecycle.LiveData
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.ClassFilter
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.views.states.ClassState

interface ClassContract{

    interface ViewModel{

        val classesState: LiveData<ClassState>

        fun fetchAll()
        fun getAll()
        fun getClassByFilter(classFilter: ClassFilter)
    }

}
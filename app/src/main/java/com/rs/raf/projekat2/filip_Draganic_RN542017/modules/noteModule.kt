package com.rs.raf.projekat2.filip_Draganic_RN542017.modules

import com.rs.raf.projekat2.filip_Draganic_RN542017.data.datasources.local.ClassDatabase
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.datasources.local.NotesDatabase
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.datasources.remote.ClassService
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.repositories.NotesRepository

import com.rs.raf.projekat2.filip_Draganic_RN542017.data.repositories.NotesRepositoryImpl
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.viewmodel.ClassViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val notesModule = module {
    viewModel{ ClassViewModel(classRepository = get() ) }

    single<NotesRepository> { NotesRepositoryImpl(localDataSource = get()) }

    single {get<NotesDatabase>().getNotesDao()}


}
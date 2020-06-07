package com.rs.raf.projekat2.filip_Draganic_RN542017.modules

import com.rs.raf.projekat2.filip_Draganic_RN542017.data.datasources.local.NotesDatabase
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.repositories.NotesRepository
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.repositories.NotesRepositoryImpl
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.viewmodel.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val notesModule = module {
    viewModel{ NoteViewModel(get()) }

    single<NotesRepository> { NotesRepositoryImpl(get()) }

    single {get<NotesDatabase>().getNotesDao()}

}
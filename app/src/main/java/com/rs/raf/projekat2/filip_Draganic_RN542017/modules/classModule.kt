package com.rs.raf.projekat2.filip_Draganic_RN542017.modules

import com.rs.raf.projekat2.filip_Draganic_RN542017.data.datasources.local.ClassDatabase
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.datasources.remote.ClassService
import com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.viewmodel.ClassViewModel
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.repositories.ClassRepository
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.repositories.ClassRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val classModule = module {
    viewModel{ ClassViewModel(classRepository = get() )}

    single<ClassRepository> { ClassRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single {get<ClassDatabase>().getClassDao()}

    single<ClassService> {create(retrofit = get())}



}
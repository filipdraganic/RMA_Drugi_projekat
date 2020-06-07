package com.rs.raf.projekat2.filip_Draganic_RN542017.data.repositories

import com.rs.raf.projekat2.filip_Draganic_RN542017.data.datasources.local.ClassDao
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.datasources.remote.ClassService
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Class
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.ClassEntity
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.ClassFilter
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Resource
import io.reactivex.Completable
import io.reactivex.Observable
import timber.log.Timber


class ClassRepositoryImpl (private val localDataSource: ClassDao, private val remoteDataSource: ClassService) : ClassRepository {


    override fun fetchAll(): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAll()
            .doOnNext {
                Timber.e("Fetchovanje svih u implementaciji")
                val entitites = it.map {
                    ClassEntity(
                        0,
                        it.predmet,
                        it.nastavnik,
                        it.tip,
                        it.dan,
                        it.termin,
                        it.ucionica,
                        it.grupe
                    )
                }
                localDataSource.deleteAndInsertAll(entitites)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getAll(): Observable<List<Class>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    Class(it.id, it.predmet, it.nastavnik, it.tip, it.dan, it.termin, it.ucionica, it.grupe)
                }
            }

    }

    override fun insert(klasa: Class): Completable {

        val classEntity = ClassEntity(klasa.id, klasa.predmet, klasa.nastavnik, klasa.tip, klasa.dan, klasa.termin, klasa.ucionica, klasa.grupe)
        return localDataSource
            .insert(classEntity)

    }

    override fun getClassByFilter(classFilter: ClassFilter): Observable<List<Class>> {
        Timber.e("GLEDAJ OVDE -> day:"+classFilter.dan + "group:"+classFilter.grupe + "query:"+classFilter.searchQuery)
        return localDataSource
            .getClassByFilter(classFilter.grupe, classFilter.dan, classFilter.searchQuery)
            .map {
                it.map {
                    Class(it.id, it.predmet, it.nastavnik, it.tip, it.dan, it.termin, it.ucionica, it.grupe)
                }
            }
    }


}
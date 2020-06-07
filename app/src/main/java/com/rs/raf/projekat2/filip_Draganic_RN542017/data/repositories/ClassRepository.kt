package com.rs.raf.projekat2.filip_Draganic_RN542017.data.repositories

import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Class
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.ClassFilter
import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.Resource
import io.reactivex.Completable

import io.reactivex.Observable

interface ClassRepository {

    fun fetchAll(): Observable<Resource<Unit>>
    fun getAll(): Observable<List<Class>>
    fun insert(klasa: Class): Completable
    fun getClassByFilter(classFilter: ClassFilter): Observable<List<Class>>

}
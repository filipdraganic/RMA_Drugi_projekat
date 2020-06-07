package com.rs.raf.projekat2.filip_Draganic_RN542017.data.datasources.remote

import com.rs.raf.projekat2.filip_Draganic_RN542017.data.models.ClassResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ClassService{

    @GET("json.php")
    fun getAll(@Query("limit") limit: Int = 1000): Observable<List<ClassResponse>>
}
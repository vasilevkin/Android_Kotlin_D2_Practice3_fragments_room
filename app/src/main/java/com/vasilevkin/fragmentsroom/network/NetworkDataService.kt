package com.vasilevkin.fragmentsroom.network

import com.vasilevkin.fragmentsroom.models.networkModels.CatImageRemoteModel
import com.vasilevkin.fragmentsroom.models.networkModels.CatRemoteModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface CatInterface {
    // https://api.thecatapi.com/

    @GET("v1/breeds")
    fun getAllBreeds(): Single<List<CatRemoteModel>>

    @GET("v1/images/search?limit=30&breed_id=abys")
    fun getBreedWithImages(): Single<List<CatImageRemoteModel>>

    @GET("v1/images/search")
    fun getImageForBreedId(@Query("limit") limit: Int, @Query("breed_id") breedId: String): Single<List<CatImageRemoteModel>>
}

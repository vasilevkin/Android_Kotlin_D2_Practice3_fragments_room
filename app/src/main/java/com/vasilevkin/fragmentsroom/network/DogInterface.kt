package com.vasilevkin.fragmentsroom.network

import com.vasilevkin.fragmentsroom.models.networkModels.DogBreedsListRemoteModel
import com.vasilevkin.fragmentsroom.models.networkModels.DogImageRemoteModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface DogInterface {
    // https://dog.ceo/api/

    @GET("breeds/list/all")
    fun getAllDogBreeds(): Single<DogBreedsListRemoteModel>
//    fun getAllDogBreeds(): Single<List<DogRemoteModel>>

    @GET("breed/{breed}/images/random")
    fun getRandomImageForBreed(@Path("breed") breed: String?): Single<DogImageRemoteModel>
    

//    @GET("v1/images/search?limit=30&breed_id=abys")
//    fun getBreedWithImages(): Single<List<CatImageRemoteModel>>
//
//    @GET("v1/images/search")
//    fun getImageForBreedId(@Query("limit") limit: Int, @Query("breed_id") breedId: String): Single<List<CatImageRemoteModel>>
}

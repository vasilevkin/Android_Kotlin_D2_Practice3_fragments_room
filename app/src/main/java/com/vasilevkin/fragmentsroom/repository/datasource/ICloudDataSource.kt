package com.vasilevkin.fragmentsroom.repository.datasource

import com.vasilevkin.fragmentsroom.models.networkModels.DogBreedsListRemoteModel
import io.reactivex.Single


interface ICloudDataSource {
    fun getAnimals(): Single<DogBreedsListRemoteModel>
//    fun getAnimals(): Single<List<DogRemoteModel>>
}
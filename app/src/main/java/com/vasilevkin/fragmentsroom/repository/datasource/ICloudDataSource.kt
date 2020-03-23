package com.vasilevkin.fragmentsroom.repository.datasource

import com.vasilevkin.fragmentsroom.models.networkModels.DogRemoteModel
import io.reactivex.Single


interface ICloudDataSource {
    fun getAnimals(): Single<List<DogRemoteModel>>
}
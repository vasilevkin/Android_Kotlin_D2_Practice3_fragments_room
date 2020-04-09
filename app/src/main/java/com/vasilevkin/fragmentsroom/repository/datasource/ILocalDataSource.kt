package com.vasilevkin.fragmentsroom.repository.datasource

import com.vasilevkin.fragmentsroom.models.localModels.AnimalsEntity
import io.reactivex.Single


interface ILocalDataSource {
    fun getAnimals(): Single<AnimalsEntity>
    fun saveAnimals(animalsEntity: AnimalsEntity)
}
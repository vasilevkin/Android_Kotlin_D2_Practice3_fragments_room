package com.vasilevkin.fragmentsroom.repository.datasource

import com.vasilevkin.fragmentsroom.models.localModels.AnimalsEntity
import io.reactivex.Single

class LocalDataSource(private val dao: AnimalsDao) : ILocalDataSource {
    override fun getAnimals(): Single<AnimalsEntity> {
        return dao.getPhotosEntity()
    }

    override fun saveAnimals(animalsEntity: AnimalsEntity) {
        dao.saveAnimalsEntity(animalsEntity)
    }
}

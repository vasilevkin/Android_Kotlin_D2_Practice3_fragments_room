package com.vasilevkin.fragmentsroom.repository

import com.vasilevkin.fragmentsroom.models.localModels.Animal
import io.reactivex.Single


interface IAnimalRepository {
    fun getAllAnimals(): Single<List<Animal>>
}
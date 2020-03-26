package com.vasilevkin.fragmentsroom.repository

import com.google.gson.GsonBuilder
import com.vasilevkin.fragmentsroom.MainApplication
import com.vasilevkin.fragmentsroom.models.localModels.Animal
import com.vasilevkin.fragmentsroom.models.localModels.AnimalsEntity
import com.vasilevkin.fragmentsroom.models.networkModels.Breed
import com.vasilevkin.fragmentsroom.repository.datasource.*
import com.vasilevkin.fragmentsroom.utils.CACHE_VALID_TIME_IN_MILLIS
import com.vasilevkin.fragmentsroom.utils.getCurrentTime
import com.vasilevkin.fragmentsroom.utils.getDataServiceCommon
import com.vasilevkin.fragmentsroom.utils.getDogDataServiceCommon
import io.reactivex.Single
import org.json.JSONArray
import org.json.JSONObject


class AnimalRepository(
//    private val localDataSource: ILocalDataSource,
//    private val cloudDataSource: ICloudDataSource
) : IAnimalRepository {

    private val animalsDao = MainApplication.database?.animalsDao()
    private val localDataSource: ILocalDataSource = LocalDataSource(animalsDao!!)
    private val cloudDataSource: ICloudDataSource = CloudDataSource()

    private var breedList = mutableListOf<Breed>()

    private fun isCacheValid(time: Long): Boolean {
        return (getCurrentTime() - time) < CACHE_VALID_TIME_IN_MILLIS
    }

    private fun parseToAnimalsEntity(animals: List<Animal>): AnimalsEntity {
        return AnimalsEntity(animals = animals.map { it }, time = getCurrentTime())
    }

    override fun getAnimals(): Single<List<Animal>> {
        return localDataSource.getAnimals().flatMap { animalsEntity ->
            if (isCacheValid(animalsEntity.time)) Single.just(animalsEntity.animals.map { it })
            else throw CacheIsNotValidException()
        }.onErrorResumeNext {
            //            cloudDataSource.getPhotos().map {
            getAllAnimals().map {
                it.map {
                    it
                }.also {
                    localDataSource.saveAnimals(parseToAnimalsEntity(it))
                }
            }
        }
    }


    override fun getAllAnimals(): Single<List<Animal>> {

        val catsApi = getDataServiceCommon()

        val dogsApi = getDogDataServiceCommon()

        val dogResponse = dogsApi.getAllDogBreeds()
//        val response = catsApi.getAllBreeds()


        return dogResponse
            .map { dogsFromNetwork ->
                return@map getBreedObjList(dogsFromNetwork.breedsList!!)
            }
            .map { list ->
                val arr = ArrayList<Animal>(5)
                for (dog in list.indices) {
                    dogsApi.getRandomImageForBreed(list[dog].breed)
                        .subscribe { singleDog ->
                            arr.add(
                                Animal(
                                    list[dog].breed.capitalize(),
                                    list[dog].breed,
                                    singleDog.imageUrl.orEmpty()
                                )
                            )
                        }
                }
                return@map arr
            }
    }


    private fun getBreedObjList(obj: Any): List<Breed> {
        var list = mutableListOf<Breed>()
        val gson1 = GsonBuilder().setPrettyPrinting().create()
        val jsonStr: String = gson1.toJson(obj)
        val jsonObj = JSONObject(jsonStr)
        var keys = jsonObj.keys()

        while (keys.hasNext()) {
            var key = keys.next()
            var value: Any = jsonObj.get((key))
            val subBreeds = value.toString()

            var subBreedList = mutableListOf<String>()

            if (subBreeds.isNotBlank()) {
                val jsonarray = JSONArray(subBreeds)
                for (i in 0 until jsonarray.length()) {
                    val subBreedStr = jsonarray.getString(i)
                    subBreedList.add(subBreedStr)
                }
            }

            var objBreed = object : Breed(key, subBreedList) {}
            list.add(objBreed)
        }

        breedList = list
        return breedList
    }

}

package com.vasilevkin.fragmentsroom.repository.datasource

import com.vasilevkin.fragmentsroom.models.networkModels.DogBreedsListRemoteModel
import com.vasilevkin.fragmentsroom.models.networkModels.DogRemoteModel
import com.vasilevkin.fragmentsroom.utils.getDogDataServiceCommon
import io.reactivex.Single


class CloudDataSource : ICloudDataSource {
    override fun getAnimals(): Single<DogBreedsListRemoteModel> {
//    override fun getAnimals(): Single<List<DogRemoteModel>> {
        return getDogDataServiceCommon().getAllDogBreeds()
    }
}

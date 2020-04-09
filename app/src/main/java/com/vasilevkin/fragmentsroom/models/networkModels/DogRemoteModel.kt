package com.vasilevkin.fragmentsroom.models.networkModels

import com.google.gson.annotations.SerializedName


data class DogBreedsListRemoteModel(
    @SerializedName("message") val breedsList: Any? = null,
    @SerializedName("status") val status: String? = null
)

open class Breed(var breed: String, var subBreed: List<String>)

data class DogRemoteModel(
    @SerializedName("name") val name: String? = null,
//    @SerializedName("origin") val origin: String? = null,
    @SerializedName("id") val breedId: String? = null
)


data class DogImageRemoteModel(
    @SerializedName("message") val imageUrl: String? = null,
    @SerializedName("status") val status: String? = null
)


data class DogAllImagesListRemoteModel(
    @SerializedName("message") val images: List<String>? = null,
    @SerializedName("status") val status: String? = null
)

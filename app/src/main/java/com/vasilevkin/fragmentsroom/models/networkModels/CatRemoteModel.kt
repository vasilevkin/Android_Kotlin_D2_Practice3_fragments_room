package com.vasilevkin.fragmentsroom.models.networkModels

import com.google.gson.annotations.SerializedName


data class CatRemoteModel(
    @SerializedName("name") val name: String? = null,
    @SerializedName("origin") val origin: String? = null,
    @SerializedName("id") val breedId: String? = null
)


data class CatImageRemoteModel(
    @SerializedName("breeds") val breeds: List<Breeds?>? = null,
    @SerializedName("url") val imageUrl: String? = null
)


data class Breeds(
    @SerializedName("name") val name: String? = null,
    @SerializedName("origin") val origin: String? = null
)

package com.vasilevkin.fragmentsroom.models.localModels

import java.io.Serializable

data class Animal(
    val title: String? = null,
    val subtitle: String? = null,
    val imageUrl: String? = null
) : Serializable
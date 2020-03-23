package com.vasilevkin.fragmentsroom.models.localModels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vasilevkin.fragmentsroom.models.localModels.Animal


@Entity
data class AnimalsEntity(
    val animals: List<Animal>,
    val time: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
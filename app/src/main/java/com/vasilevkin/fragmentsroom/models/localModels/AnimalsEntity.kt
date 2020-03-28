package com.vasilevkin.fragmentsroom.models.localModels

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class AnimalsEntity(
    val animals: List<Animal>,
    val time: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
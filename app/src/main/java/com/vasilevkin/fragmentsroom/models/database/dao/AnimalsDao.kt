package com.vasilevkin.fragmentsroom.models.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vasilevkin.fragmentsroom.models.localModels.AnimalsEntity
import io.reactivex.Single


@Dao
interface AnimalsDao {
    @Query("SELECT * FROM AnimalsEntity")
    fun getAnimalsEntity(): Single<AnimalsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAnimalsEntity(animalsEntity: AnimalsEntity)
}
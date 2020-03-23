package com.vasilevkin.fragmentsroom.models.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vasilevkin.fragmentsroom.models.database.AppDatabase.Companion.DB_VERSION
import com.vasilevkin.fragmentsroom.models.database.dao.AnimalsDao
import com.vasilevkin.fragmentsroom.models.localModels.AnimalsEntity
import com.vasilevkin.fragmentsroom.models.localModels.converters.Converters


@Database(entities = [AnimalsEntity::class], version = DB_VERSION, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_VERSION = 1
    }

    abstract fun animalsDao(): AnimalsDao
}
package com.vasilevkin.fragmentsroom.models.localModels.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vasilevkin.fragmentsroom.models.localModels.Animal
import java.util.ArrayList


class Converters {
    @TypeConverter
    fun fromList(list: List<Animal>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toList(json: String): List<Animal> {
        val listType = object : TypeToken<ArrayList<Animal>>() {}.type
        return Gson().fromJson<List<Animal>>(json, listType)
    }
}
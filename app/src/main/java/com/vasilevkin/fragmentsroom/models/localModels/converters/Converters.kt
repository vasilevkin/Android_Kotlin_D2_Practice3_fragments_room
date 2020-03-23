package com.vasilevkin.fragmentsroom.models.localModels.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList


class Converters {
    @TypeConverter
    fun fromList(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toList(json: String): List<String> {
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson<List<String>>(json, listType)
    }
}
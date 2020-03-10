package com.koidev.persistence.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {

    @TypeConverter
    @JvmStatic
    fun fromStringList(list: ArrayList<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun toStringList(value: String): ArrayList<String> {
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, listType)
    }
}
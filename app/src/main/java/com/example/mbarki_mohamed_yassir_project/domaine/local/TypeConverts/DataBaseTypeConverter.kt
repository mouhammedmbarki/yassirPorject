package com.example.mbarki_mohamed_yassir_project.domaine.local.TypeConverts

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DataBaseTypeConverter {
    @TypeConverter
    fun stringToListInt(json: String) : List<Int>? {
        val gson = Gson()
        val type: Type?= object : TypeToken<List<Int>?>() {}.type
        return gson.fromJson(json, type)
    }


    @TypeConverter
    fun listIntToString(list : List<Int> ) : String {
        val gson = Gson()
        val type: Type? = object : TypeToken<List<Int>?>() {}.type
        return gson.toJson(list, type)
    }
}
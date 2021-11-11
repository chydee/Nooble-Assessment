package com.chidi.local.db

import androidx.room.TypeConverter
import com.chidi.local.model.CreatorLocal
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CreatorTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun creatorToString(creatorLocal: CreatorLocal): String {
        return gson.toJson(creatorLocal)
    }

    @TypeConverter
    fun stringToCreator(creatorString: String): CreatorLocal {
        val objectType = object : TypeToken<CreatorLocal>() {}.type
        return gson.fromJson(creatorString, objectType)
    }

}
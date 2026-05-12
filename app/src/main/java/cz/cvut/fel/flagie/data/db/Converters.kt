package cz.cvut.fel.flagie.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import cz.cvut.fel.flagie.data.model.CountryFlags
import cz.cvut.fel.flagie.data.model.CountryName

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromCountryName(value: CountryName?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toCountryName(value: String?): CountryName? {
        return value?.let {
            try {
                gson.fromJson(it, CountryName::class.java)
            } catch (e: Exception) {
                null
            }
        }
    }

    @TypeConverter
    fun fromCountryFlags(value: CountryFlags?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toCountryFlags(value: String?): CountryFlags? {
        return value?.let {
            try {
                gson.fromJson(it, CountryFlags::class.java)
            } catch (e: Exception) {
                null
            }
        }
    }

    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        return value?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        return value?.let {
            val type = object : TypeToken<List<String>>() {}.type
            try {
                gson.fromJson(it, type)
            } catch (e: Exception) {
                emptyList()
            }
        }
    }
}
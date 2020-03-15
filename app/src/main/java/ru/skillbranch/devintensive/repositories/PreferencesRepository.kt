package ru.skillbranch.devintensive.repositories

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import ru.skillbranch.devintensive.App
import ru.skillbranch.devintensive.models.Profile

object PreferencesRepository {

    private const val FIRST_NAME = "FIRS_TNAME"
    private const val LAST_NAME = "LAST_NAME"
    private const val CITY = "CITY"

    private val prefs: SharedPreferences by lazy {
        val ctx = App.applicationContext()
        PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    fun saveProfile(profile: Profile){
        with(profile){
            putValue(FIRST_NAME to firstName)
            putValue(LAST_NAME to lastName)
            putValue(CITY to city)
        }
    }

    fun getProfile(): Profile? = Profile(
        prefs.getString(FIRST_NAME, "")!!,
        prefs.getString(LAST_NAME, "")!!,
        prefs.getString(CITY, "")!!
    )

    private fun putValue(pair: Pair<String, Any>)= with(prefs.edit()){
        val key = pair.first
        val value = pair.second

        when(value){
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            else -> error("Только примитивные типы")
        }

        apply()
    }

}
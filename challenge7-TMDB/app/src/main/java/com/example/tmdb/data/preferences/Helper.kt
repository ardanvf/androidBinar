package com.example.tmdb.data.preferences

import android.content.Context
import android.content.SharedPreferences

class Helper(context: Context) {

    private val sharedPrefFile = "userPreference"
    private var sharedPref: SharedPreferences =
        context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPref.edit()

    fun putEmail(key: String, value: String) {
        editor.putString(key, value).apply()
    }
    fun getEmail(key: String): String? {
        return sharedPref.getString(key, null)
    }

}
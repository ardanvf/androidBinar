package com.example.loginwithsharedpreferences.helper

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {

    private var sharedpref: SharedPreferences

    val editor: SharedPreferences.Editor

    init {
        sharedpref = context.getSharedPreferences("Share", Context.MODE_PRIVATE)
        editor = sharedpref.edit()
    }

    fun put(key: String, value: String){
        editor.putString(key, value)
            .apply()
    }

    fun getString(key: String): String?{
        return sharedpref.getString(key, null)
    }

    fun putBoolean(key: String, value: Boolean){
        editor.putBoolean(key, value)
            .apply()
    }

    fun getBoolean(key: String): Boolean?{
        return sharedpref.getBoolean(key, false)
    }

}
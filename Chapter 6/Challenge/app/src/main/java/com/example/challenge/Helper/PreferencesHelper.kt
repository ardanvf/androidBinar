package com.example.challenge.Helper

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {

    private val PREFS_NAME = "ksadoksa"
    private var sharedpref: SharedPreferences
    val editor: SharedPreferences.Editor

    init{
        sharedpref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedpref.edit()
    }

    fun put(key: String, value: String){
        editor.putString(key, value)
            .apply()
    }

    fun getString(key: String) : String? {
        return sharedpref.getString(key, null)
    }

    fun putBool(key: String, value: Boolean){
        editor.putBoolean(key, value)
            .apply()
    }

    fun getBool(key: String) : Boolean {
        return sharedpref.getBoolean(key, false)
    }

    fun clear(){
        editor.clear()
            .apply()
    }

}
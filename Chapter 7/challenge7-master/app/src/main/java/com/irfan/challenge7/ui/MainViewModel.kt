package com.irfan.challenge7.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.irfan.challenge7.storage.PreferencesManager

class MainViewModel(private val preferencesManager: PreferencesManager) : ViewModel() {

    val isLoggedIn = preferencesManager.isLoggedIn().asLiveData()
}
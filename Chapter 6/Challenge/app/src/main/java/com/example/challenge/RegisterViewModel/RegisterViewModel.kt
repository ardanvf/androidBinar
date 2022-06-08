package com.example.challenge.RegisterViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challenge.datastore.CounterDataStoreManager

class RegisterViewModel(private val pref: CounterDataStoreManager) : ViewModel(){
    val vCounter: MutableLiveData<Int> = MutableLiveData(0)

}
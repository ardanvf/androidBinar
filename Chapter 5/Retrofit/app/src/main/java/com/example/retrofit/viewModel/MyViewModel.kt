package com.example.retrofit.viewModel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofit.data.GetAllCarResponseItem

class MyViewModel() {
    private val users: MutableLiveData<List<GetAllCarResponseItem>> by lazy{
        MutableLiveData<List<GetAllCarResponseItem>>().also {
            loadCar()
        }
    }

    fun getCars(): LiveData<List<GetAllCarResponseItem>>{
        return getCars()
    }

    private fun loadCar(){

    }
}
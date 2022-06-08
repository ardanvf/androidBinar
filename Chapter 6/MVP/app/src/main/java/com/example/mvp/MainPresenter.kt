package com.example.mvp

interface MainPresenter {
    //method untuk menambah data
    fun add(firstName: String, lastName: String)

    //method untuk memproses data
    fun loadData()
}
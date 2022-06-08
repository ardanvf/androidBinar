package com.example.mvp

interface MainView {

    //method menampilkan pesan toast
    fun showMessage(message: String)

    //method menampilkan data yang telah diproses
    fun showData(data: String)

    //method clear edittext
    fun clearField()
}
package com.example.mvp.Model

import android.text.TextUtils
import com.example.mvp.MainPresenter
import com.example.mvp.MainView

class MainPresenterImp(private val view: MainView) : MainPresenter {

    private val STUDENT = mutableListOf<Student>()

    override fun add(firstName: String, lastName: String) {
        if(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName)){
            view.showMessage("Field tidak boleh kosong")
        } else {
            STUDENT.run {
                add(Student(firstName,lastName))
            }
            view.showMessage("Data ditambahkan")

            view.clearField()
        }
    }

    override fun loadData() {
        if(STUDENT.size == 0){
            view.showMessage("Data masih kosong")
        } else {
            var allData = ""

            for(i in 0 until STUDENT.size){
                allData += "Nama depan : " + STUDENT[i].firstName + "\nNama Belakang : " + STUDENT[i].lastName + "\n\n"
            }
            allData += "Total : " + STUDENT.size
            view.showData(allData)
        }
    }

}
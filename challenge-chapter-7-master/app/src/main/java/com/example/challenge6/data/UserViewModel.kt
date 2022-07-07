package com.example.challenge6.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge6.data.dao.UserDao
import com.example.challenge6.data.entity.Login

class UserViewModel(private val userDao: UserDao) : ViewModel() {

    // register
    fun userProfile(name: String, email: String, password: String) {
        val data = dataEntry(name, email, password)
        insertToDatabase(data)
    }

    fun userProfile(id: Int,name: String, email: String, password: String): Int {
        val data = dataEntry(id, name, email, password)
        return updateData(data)
    }

    private fun dataEntry(id: Int, name: String, email: String, password: String): Login {
        return Login(id, name, email, password)
    }

    private fun dataEntry(name: String, email: String, password: String): Login {
        return Login(
            id = null, name, email, password
        )
    }

    private fun insertToDatabase(data: Login) {
        userDao.registerUser(data)
    }

    fun checkUserExists(email: String, password: String): Boolean {
        return userDao.getUser(email, password)
    }

    fun getUserProfile(email: String?): Login {
        return userDao.getProfile(email)
    }

    fun getUserName(email: String): String {
       return userDao.getName(email)
    }

    private fun updateData(data: Login): Int {
       return userDao.updateData(data)
    }

    fun isInputEmpty(
        name: String,
        email: String,
        password: String,
        passwordConfirm: String
    ): Boolean {
        return !(name.isBlank() || email.isBlank() || password.isBlank() || passwordConfirm.isBlank())
    }

    fun isInputEmpty(
        email: String,
        password: String
    ): Boolean {
        return !(email.isBlank() || password.isBlank())
    }
}

class UserViewModelFactory(private val userDao: UserDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
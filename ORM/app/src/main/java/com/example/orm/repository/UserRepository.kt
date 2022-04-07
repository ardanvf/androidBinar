package com.example.orm.repository

import androidx.lifecycle.LiveData
import com.example.orm.data.UserDao
import com.example.orm.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
}
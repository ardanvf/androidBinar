package com.example.tmdb.data.dao

import androidx.room.*
import com.example.tmdb.data.entity.Login

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun registerUser(userData: Login)

    @Query("Select Exists(Select * From Login Where email = :email And password = :password)")
    fun getUser(email: String, password: String): Boolean

    @Query("Select * From Login Where email = :email")
    fun getProfile(email: String?): Login

    @Query("Select name From Login Where email = :email")
    fun getName(email: String): String

    @Update
    fun updateData(updatedData: Login): Int
}
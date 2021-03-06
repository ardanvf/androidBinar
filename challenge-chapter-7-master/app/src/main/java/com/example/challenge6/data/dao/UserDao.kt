package com.example.challenge6.data.dao

import androidx.room.*
import com.example.challenge6.data.entity.Login

@Dao
interface UserDao {

    //register
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun registerUser(userData: Login)

    //cek user exist
    @Query("Select Exists(Select * From Login Where email = :email And password = :password)")
    fun getUser(email: String, password: String): Boolean

    //get user data on profile page
    @Query("Select * From Login Where email = :email")
    fun getProfile(email: String?): Login

    @Query("Select name From Login Where email = :email")
    fun getName(email: String): String

    //update
    @Update
    fun updateData(updatedData: Login): Int
    //delete
}
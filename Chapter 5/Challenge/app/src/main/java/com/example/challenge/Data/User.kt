package com.example.challenge.Data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val email: String,
    val address: String,
    val password: String,
    val date: String

) : Parcelable

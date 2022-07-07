package com.example.tmdb.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Login(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String,
    val fname:String,
    val date:String,
    val adress:String,
    val email: String,
    val password: String,
    val pitcure:String
) : Parcelable

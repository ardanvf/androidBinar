package com.example.challenge6.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity
@Parcelize
data class Login(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String,
    val email: String,
    val password: String
) : Parcelable

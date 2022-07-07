package com.example.tmdb.ui.shared.mapper

interface Mapper<in T : Any, out R : Any> {
    fun map(dataIn: T): R
}
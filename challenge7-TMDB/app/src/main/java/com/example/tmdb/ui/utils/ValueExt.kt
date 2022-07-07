package com.example.tmdb.ui.utils

fun String?.orIfEmpty(value: String): String {
    return this.orEmpty().ifEmpty { value }
}
package com.example.test_json

data class Data(
    val amount: Int,
    val change: Int,
    val detail: List<Detail>,
    val paid: Int,
    val qty: Int
)
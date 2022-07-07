package com.irfan.challenge7

import com.irfan.challenge7.domain.model.Movie


object DataDummy {

    fun generateDummyMovie(): List<Movie> {
        val items: MutableList<Movie> = arrayListOf()
        for (i in 1..50) {
            val quote = Movie(
                i.toLong(),
                "Original title + $i",
                "overview $i",
                "/7MDgiFOPUCeG74nQsMKJuzTJrtc.jpg",
                "/hXTWVJMsI9BkxMLliqL1j0FT55t.jpg",
                "2022-04-08",
                "title $i",
                "6.1"
            )
            items.add(quote)
        }
        return items
    }
}
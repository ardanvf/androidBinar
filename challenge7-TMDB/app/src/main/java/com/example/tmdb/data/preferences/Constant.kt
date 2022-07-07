package com.example.tmdb.data.preferences

class Constant {
    companion object {
        const val EMAIL_USER = "email"
        const val API_KEY = "3f4e6c7a45f1cac5b12c889c1f53185a"
        const val IMAGE_500_URL = "https://image.tmdb.org/t/p/w500"

        const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
        const val TMDB_POPULARITY = "popularity.desc"

        const val MOVIE_KEY = "movie_id"
        const val MOVIE_TITLE_KEY = "movie_title"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
        const val YOUTUBE_WEB_URL = "https://www.youtube.com/watch?v="
        const val IMAGE_SIZE_W185 = "w185"
        const val BACKDROP_SIZE = "w780"
        const val IMAGE_URL = IMAGE_BASE_URL + IMAGE_SIZE_W185
        const val BACKDROP_URL = IMAGE_BASE_URL + BACKDROP_SIZE
        const val VIEW_TYPE_ITEM = 0
        const val VIEW_TYPE_LOADING = 1
    }

}
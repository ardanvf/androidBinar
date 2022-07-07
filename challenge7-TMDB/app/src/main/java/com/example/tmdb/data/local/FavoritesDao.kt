package com.example.tmdb.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.tmdb.data.local.entity.FavoriteMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Insert
    suspend fun insert(movie: FavoriteMovie)

    @Query("SELECT * FROM favorite_movie_table")
    fun getFavoriteMovies(): Flow<List<FavoriteMovie>>

    @Query("SELECT * FROM favorite_movie_table WHERE movie_id = :id")
    fun getFavoriteById(id: Int): Flow<FavoriteMovie?>

    @Query("DELETE FROM favorite_movie_table WHERE movie_id = :id")
    suspend fun delete(id: Int)

}
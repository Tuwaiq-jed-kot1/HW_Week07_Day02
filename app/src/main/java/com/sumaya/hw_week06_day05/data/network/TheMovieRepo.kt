package com.sumaya.hw_week06_day05.data.network

import com.sumaya.hw_week06_day05.data.models.TheMovieData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TheMovieRepo {
    private val api = TheMovieBuilder.theMovieAPI

    suspend fun fetchMovies(): TheMovieData = withContext(Dispatchers.IO) {
        api.fetchMovies()
    }

    suspend fun searchMovies(searchKeyword: String): TheMovieData = withContext(Dispatchers.IO) {
        api.searchMovies(searchKeyword)
    }

}
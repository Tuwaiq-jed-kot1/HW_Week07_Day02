package com.sumaya.hw_week06_day05.data.network

import com.sumaya.hw_week06_day05.data.modules.MoviesData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepo {
     val api = MoviesBuilder.moviesAPI

    suspend fun fetchList(): MoviesData = withContext(Dispatchers.IO) {
        api.fetchMovies()
    }
    suspend fun searchPhotos(searchKeyWord: String): MoviesData = withContext(Dispatchers.IO) {
        api.searchMovies(searchKeyWord)
    }
}
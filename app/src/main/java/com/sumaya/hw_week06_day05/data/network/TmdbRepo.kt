package com.sumaya.hw_week06_day05.data.network

import com.sumaya.hw_week06_day05.data.models.TmdbListMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TmdbRepo {
    private val api = TmdbBuilder.tmdbApi

    suspend fun fetchList(): TmdbListMovies = withContext(Dispatchers.IO){
        api.fetchMovie()
    }

    suspend fun searchMovies(searchKeyword: String): TmdbListMovies = withContext(Dispatchers.IO){
        api.searchMovies(searchKeyword)
    }
}
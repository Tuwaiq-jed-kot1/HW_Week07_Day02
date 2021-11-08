package com.sumaya.hw_week06_day05.network

import com.sumaya.hw_week06_day05.MoviesData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepo {
    private val api = MovieBuilder.movieApi

    suspend fun fetchInterestingList(): MoviesData = withContext(Dispatchers.IO) {
        api.fetchMovie()
    }
    suspend fun searchMovie(searchKeyword: String): MoviesData = withContext(Dispatchers.IO) {
        api.searchMovie(searchKeyword)
    }
}
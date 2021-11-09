package com.shady.restandretrofit.data.network


import com.sumaya.hw_week06_day05.JsonData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepo {
    private val api = MovieBuilder.MovieAPI

    suspend fun fetchInterestingList(): JsonData = withContext(Dispatchers.IO) {
        api.fetchMovie()
    }
    suspend fun searchMovie(searchKeyword: String): JsonData = withContext(Dispatchers.IO) {
        api.searchMovie(searchKeyword)
    }
}
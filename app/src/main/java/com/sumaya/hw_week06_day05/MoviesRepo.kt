package com.sumaya.hw_week06_day05

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepo {
    private val api = MoviesBuilder.moviesAPI
    suspend fun fetchMovies(): MoviesData= withContext(Dispatchers.IO){
        api.fetchMovies()
    }

    suspend fun searchMovies (searchKeyword: String): MoviesData= withContext(Dispatchers.IO){
        api.searchMovies(searchKeyword)
    }


}
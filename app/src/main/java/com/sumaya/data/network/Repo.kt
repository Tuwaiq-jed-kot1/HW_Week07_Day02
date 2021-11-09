package com.sumaya.hw_week06_day05.data.network

import MoviesDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repo {
private val api=Builder.moviesAPI
    suspend fun fetchMovies():MoviesDB= withContext(Dispatchers.IO){
        api.fetchMovies()
    }


    suspend fun searchMovie(searchKey:String):MoviesDB= withContext(Dispatchers.IO){
        api.searchMovie(searchKey)
    }
}
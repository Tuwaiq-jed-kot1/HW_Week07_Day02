package com.sumaya.hw_week06_day05


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepo {
    private val api=Builder.moviesAPI
    suspend fun fetchMovie():MoviesDB= withContext(Dispatchers.IO){
        api.fetchMovie()
    }


    suspend fun searchMovie(searchKey:String):MoviesDB= withContext(Dispatchers.IO){
        api.searchMovie(searchKey)
    }
}
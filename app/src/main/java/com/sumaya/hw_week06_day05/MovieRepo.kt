package com.sumaya.hw_week06_day05

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepo {
    private val api= MovieBuilder.movieApi
    suspend fun getAllmovies():MovieRoot = withContext(Dispatchers.IO){
        api.getAllmovies()}

    suspend fun searchInMovies(searchKeyword:String):MovieRoot = withContext(Dispatchers.IO){
        api.searchInMovies(searchKeyword)
    }
}
package com.sumaya.hw_week06_day05.Data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepo {
private val tag ="MovieRepo"
   private val api = MovieBulier.MovieApi

suspend fun fetchIntrestingList() : Data = withContext(Dispatchers.IO){
    api.fetchMovie()
}
suspend fun seachMovie(searchKeyword:String):Data = withContext(Dispatchers.IO){
    api.seachMovie(searchKeyword)
}
    suspend fun movieID():Data = withContext(Dispatchers.IO){
        api.movieId()
    }
}
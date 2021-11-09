package com.sumaya.hw_week06_day05

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface MovieApi {
        @GET("https://api.themoviedb.org/3/movie/popular?api_key=3ff78960ca487af90ebd24ff4a46caf8&language=en-US&page=1")
        suspend fun fetchMovie(): MoviesDB

        @GET("?method=flickr.photos.search&api_key=3ff78960ca487af90ebd24ff4a46caf8&language=en-US&page=1&format=json&nojsoncallback=1&extras=url_s&safe_search=1")
        suspend fun searchMovie(@Query("query") searchKeyword:String): MoviesDB
}


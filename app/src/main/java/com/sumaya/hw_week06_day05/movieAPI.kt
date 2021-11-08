package com.sumaya.hw_week06_day05

import retrofit2.http.GET
import retrofit2.http.Query

interface movieAPI {
    @GET("movie/popular?api_key=d490e2cc02730711d54e8d0e1d27ab85&language=en-US&page=1")
    suspend fun getAllmovies(): MovieRoot

    @GET("search/movie?api_key=d490e2cc02730711d54e8d0e1d27ab85&language=en-US&page=1&include_adult=false")
    suspend fun searchInMovies(@Query("query") searchKey:String ): MovieRoot

}
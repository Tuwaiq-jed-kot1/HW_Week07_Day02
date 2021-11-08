package com.sumaya.hw_week06_day05.network

import com.sumaya.hw_week06_day05.MoviesData
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET("movie/popular?api_key=5eb0c8ee18b9c8a10c8b31bdd8905e03&language=en-US&page=1")
    suspend fun fetchMovie(): MoviesData

    @GET("search/movie?api_key=5eb0c8ee18b9c8a10c8b31bdd8905e03&language=en-US&page=1&include_adult=false")
    suspend fun searchMovie(@Query("query") searchKeyword:String): MoviesData
}
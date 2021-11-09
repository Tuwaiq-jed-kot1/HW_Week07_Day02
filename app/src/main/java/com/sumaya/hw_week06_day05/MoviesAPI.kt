package com.sumaya.hw_week06_day05

import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {
    @GET("movie/popular?api_key=6dbec61ef570e2f7827498c520c1904a&language=en-US&page=1")
    suspend fun fetchMovies(): MoviesData

    @GET("search/movie?api_key=6dbec61ef570e2f7827498c520c1904a&language=en-US&page=1&include_adult=false")
    suspend fun searchMovies(@Query("query") searchKeyword:String): MoviesData
}
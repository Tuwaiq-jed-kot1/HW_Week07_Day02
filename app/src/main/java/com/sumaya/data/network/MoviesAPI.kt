package com.sumaya.hw_week06_day05.data.network

import MoviesDB
import com.sumaya.hw_week06_day05.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {
    @GET("movie/popular?api_key=${BuildConfig.MovieAPIKey}&language=en-US&page=1")
    suspend fun fetchMovies(): MoviesDB


    @GET("search/movie?api_key=${BuildConfig.MovieAPIKey}&language=en-US&page=1&include_adult=false")
    suspend fun searchMovie(@Query("query") searchKey: String): MoviesDB

}



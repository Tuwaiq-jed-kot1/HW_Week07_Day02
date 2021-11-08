package com.sumaya.hw_week06_day05.data.network

import com.sumaya.hw_week06_day05.BuildConfig
import com.sumaya.hw_week06_day05.data.models.TheMovieDBData
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDBAPI {

    @GET("movie/popular?api_key=${BuildConfig.TheMovieDB_Key}&language=en-US&page=1")
    suspend fun fetchMovies(): TheMovieDBData

    @GET("search/movie?api_key=${BuildConfig.TheMovieDB_Key}&language=en-US&page=1&include_adult=false")
    suspend fun searchMovies(@Query("query") searchKeyword:String): TheMovieDBData
}
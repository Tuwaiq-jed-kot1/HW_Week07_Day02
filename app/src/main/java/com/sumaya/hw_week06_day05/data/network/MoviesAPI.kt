package com.sumaya.hw_week06_day05.data.network

import com.sumaya.hw_week06_day05.data.modules.MoviesData
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {

    @GET("https://api.themoviedb.org/3/movie/popular?api_key=5583ab969b7f87f4041833ad9128b7e3&language=en-US&page=1")
    suspend fun fetchMovies(): MoviesData

    @GET("https://api.themoviedb.org/3/search/movie?api_key=5583ab969b7f87f4041833ad9128b7e3&language=en-US&page=1&include_adult=false")
    suspend fun searchMovies(@Query("query") searchKey:String ): MoviesData
}
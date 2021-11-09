package com.sumaya.hw_week06_day05.data.network

import com.sumaya.hw_week06_day05.BuildConfig
import com.sumaya.hw_week06_day05.data.models.TheMovieData
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieAPI {

    @GET("movie/popular?api_key=${BuildConfig.theMovieAPIKey}&language=en-US&page=1")
    suspend fun fetchMovies(): TheMovieData

    @GET("search/movie?api_key=${BuildConfig.theMovieAPIKey}&language=en-US&page=1&include_adult=false")
   suspend fun searchMovies(@Query("query") searchKeyword : String): TheMovieData

}
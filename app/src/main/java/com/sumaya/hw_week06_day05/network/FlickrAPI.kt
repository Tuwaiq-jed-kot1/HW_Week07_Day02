package com.tuwaiq.restandretrofit.data.network

import com.sumaya.hw_week06_day05.BuildConfig
import com.tuwaiq.restandretrofit.data.network.models.MoviesData
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrAPI {

    @GET("movie/popular?api_key=${BuildConfig.flickrApiKey}&language=en-US&page=1")
    suspend fun fetchMovies(): MoviesData

    @GET("search/movie?api_key=${BuildConfig.flickrApiKey}&language=en-US&page=1&include_adult=false")
    suspend fun searchMovies(@Query("query") searchKeyWord:String ): MoviesData
}


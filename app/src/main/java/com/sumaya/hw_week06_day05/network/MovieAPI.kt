package com.sumaya.hw_week06_day05.network

import com.sumaya.hw_week06_day05.network.models.MovieData
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("https://api.themoviedb.org/3/movie/popular?api_key=97420aca6b2b6939360978069af38af1&language=en-US&page=1")
    suspend fun fetchContent(): MovieData


    @GET("https://api.themoviedb.org/3/search/movie?api_key=97420aca6b2b6939360978069af38af1&language=en-US&page=1&include_adult=false")
    suspend fun searchQuery(@Query("query") searchKeyWord: String): MovieData
    //word text is key and it will add &text=$searchKeyword to the url
}
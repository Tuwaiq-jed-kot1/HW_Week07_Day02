package com.sumaya.hw_week06_day05.data.network

import com.sumaya.hw_week06_day05.data.models.TmdbListMovies
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbAPI {

    @GET("https://api.themoviedb.org/3/movie/popular?api_key=70a55c7f4482df693e87797f572c2005&language=en-US&page=1")
    suspend fun fetchMovie(): TmdbListMovies

    @GET("https://api.themoviedb.org/3/search/movie?api_key=70a55c7f4482df693e87797f572c2005&language=en-US&page=1&include_adult=false")
    suspend fun searchMovies(@Query("query") searchKeyword: String ): TmdbListMovies
}
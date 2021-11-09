package com.shady.restandretrofit.data.network


import com.sumaya.hw_week06_day05.JsonData
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {
    @GET("https://api.themoviedb.org/3/movie/popular?api_key=01da3d0b8493140044520703194e3af2&language=en-US&page=1")
    suspend fun fetchMovie(): JsonData

    @GET("search/movie?api_key=01da3d0b8493140044520703194e3af2&language=en-US&page=1&include_adult=false")
    suspend fun searchMovie(@Query("query") searchKeyword:String): JsonData

}
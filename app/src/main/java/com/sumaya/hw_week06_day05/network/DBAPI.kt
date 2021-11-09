package com.sumaya.hw_week06_day05.network

import com.sumaya.hw_week06_day05.modle.DbData
import retrofit2.http.GET
import retrofit2.http.Query

interface DBAPI {
    @GET("movie/popular?api_key=6a8742208deb9920af08f16748cc7206&language=en-US&page=1")
    suspend fun viewMoviesInfo(): DbData

    @GET("search/movie?api_key=6a8742208deb9920af08f16748cc7206&language=en-US&page=1&include_adult=false")
    suspend fun searchMovies(@Query("query")searchKeyWord:String): DbData

}
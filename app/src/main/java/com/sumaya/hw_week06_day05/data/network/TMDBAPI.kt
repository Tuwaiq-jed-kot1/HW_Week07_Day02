package com.sumaya.hw_week06_day05.data.network



import com.sumaya.hw_week06_day05.TheMovieDBData
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBAPI {
    @GET("https://api.themoviedb.org/3/movie/popular?api_key=843b07ca5985116864a5d457f30b2f56&language=en-US&page=1")
    suspend fun fetchPhotos(): TheMovieDBData

    @GET("https://api.themoviedb.org/3/search/movie?api_key=843b07ca5985116864a5d457f30b2f56&language=en-US&page=1&include_adult=false")
    suspend fun searchPhotos(@Query("query") searchKeyWork : String): TheMovieDBData
}
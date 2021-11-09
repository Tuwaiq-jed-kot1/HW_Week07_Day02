package com.sumaya.hw_week06_day05.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object  Builder {
    private const val BASE_URL="https://api.themoviedb.org/3/"
    private fun retrofit():Retrofit=Retrofit.Builder().baseUrl(BASE_URL).
    addConverterFactory(GsonConverterFactory.create()).build()

    val moviesAPI:MoviesAPI= retrofit().create(MoviesAPI::class.java)
}
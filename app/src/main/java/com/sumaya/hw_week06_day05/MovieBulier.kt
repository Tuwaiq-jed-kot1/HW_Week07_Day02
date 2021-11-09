package com.sumaya.hw_week06_day05.Data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieBulier {
    private const val BASE_URL ="https://api.themoviedb.org/3/movie/popular/"

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) .build()

    val MovieApi: MovieDBApi = retrofit().create(MovieDBApi::class.java)
}